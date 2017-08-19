

//js直连oss服务 没有授权



/*	
<!-- oss begin-->
	<link rel="stylesheet" type="text/css" href="${ctxtPath}/js/oss/style.css"/>
	<!-- oss end-->

	<!-- oss begin-->
	<script type="text/javascript" src="${ctxtPath}/js/oss/lib/crypto1/crypto/crypto.js"></script>
	<script type="text/javascript" src="${ctxtPath}/js/oss/lib/crypto1/hmac/hmac.js"></script>
	<script type="text/javascript" src="${ctxtPath}/js/oss/lib/crypto1/sha1/sha1.js"></script>
	<script type="text/javascript" src="${ctxtPath}/js/oss/lib/base64.js"></script>
	<script type="text/javascript" src="${ctxtPath}/js/oss/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
	<script type="text/javascript" src="${ctxtPath}/js/oss/upload.js"></script>
	<!-- oss end-->
	
	
	<form name=theform><!-- 上传文件名称 -->
	<input type="radio" name="myradio" value="local_name" checked=true style="display:none"/>
	<input type="radio" name="myradio" value="random_name" style="display:none"/>
	<br/><!-- 上传文件地址 -->
	<input type="text" id='dirname' placeholder="如果不填，默认是上传到根目录" size=50 style="display:none">
</form>
	<div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>
		<div id="container">
		<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
		<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
	</div>
	<pre id="console"></pre>

*/


//accessid= '6MKOqxGiGU4AUk44';
//accesskey= 'ufu7nS8kS59awNihtjSonMETLI0KLy';
//host = 'http://post-test.oss-cn-hangzhou.aliyuncs.com';

//dudu-dev
//accessid= 'bype3qMTdBaJC8z9';
//accesskey= 'FMSmWRDSVkJqqoUf4ho3clGn2cnuNf';
//host = 'http://dudusrc.oss-cn-shanghai.aliyuncs.com';

//oss
//accessid= ossCallbackInfoObj.accessKeyId;
//accesskey= ossCallbackInfoObj.accessKeySecret;
//host = 'http://dudusrc.oss-cn-shanghai.aliyuncs.com';


var callbackText = {
		"callbackUrl":"http://dev.duduchewang.cn:8090/api/info/upload",
		"callbackBody":"bucket=${bucket}&object=${object}&etag=${etag}&size=${size}&mimeType=${mimeType}&imageInfo.height=${imageInfo.height}&imageInfo.width=${imageInfo.width}&imageInfo.format=${imageInfo.format}"
};

var callbackBase64 = Base64.encode(JSON.stringify(callbackText))
g_dirname = ''
g_object_name = ''
g_object_name_type = ''
now = timestamp = Date.parse(new Date()) / 1000; 


var policyText = {
	    "expiration": "2020-01-01T12:00:00.000Z", //设置该Policy的失效时间，超过这个失效时间之后，就没有办法通过这个policy上传文件了
	    "conditions": [ 
	                    {"bucket":"dudusrc"},
//	                   	{"callback":callbackBase64 },
	                   	["content-length-range", 0, 1048576000] // 设置上传文件的大小限制
	                   ]
		};

var policyBase64 = Base64.encode(JSON.stringify(policyText))

message = policyBase64
var bytes = Crypto.HMAC(Crypto.SHA1, message, accesskey, { asBytes: true }) ;
var signature = Crypto.util.bytesToBase64(bytes);

function check_object_radio() {
    var tt = document.getElementsByName('myradio');
    for (var i = 0; i < tt.length ; i++ ){
        if(tt[i].checked){
            g_object_name_type = tt[i].value;
            break;
        }
    }
}

function get_dirname()
{
    dir = document.getElementById("dirname").value;
    if (dir != '' && dir.indexOf('/') != dir.length - 1)
    {
        dir = dir + '/'
    }
    //alert(dir)
    g_dirname = dir
}

function random_string(len) {
　　len = len || 32;
　　var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';   
　　var maxPos = chars.length;
　　var pwd = '';
　　for (i = 0; i < len; i++) {
    　　pwd += chars.charAt(Math.floor(Math.random() * maxPos));
    }
    return pwd;
}

function get_suffix(filename) {
    pos = filename.lastIndexOf('.')
    suffix = ''
    if (pos != -1) {
        suffix = filename.substring(pos)
    }
    return suffix;
}

function calculate_object_name(filename)
{
    if (g_object_name_type == 'local_name')
    {
        g_object_name += "${filename}"
    }
    else if (g_object_name_type == 'random_name')
    {
        suffix = get_suffix(filename)
        g_object_name = g_dirname + random_string(10) + suffix
    }
    return ''
}

function get_uploaded_object_name(filename)
{
    if (g_object_name_type == 'local_name')
    {
        tmp_name = g_object_name
        tmp_name = tmp_name.replace("${filename}", filename);
        return tmp_name
    }
    else if(g_object_name_type == 'random_name')
    {
        return g_object_name
    }
}

/**
 * oss上传
 * @param up
 * @param filename
 * @param ret
 */
function set_upload_param(up, filename, ret){
    g_object_name = g_dirname;
    if (filename != '') {
        suffix = get_suffix(filename)
        calculate_object_name(filename)
    }
    new_multipart_params = {
        'key' : g_object_name,
        'policy': policyBase64,
//        'x-oss-callback-var': callbackBase64,
//        'callback-var':callbackBase64-vars,
        'callback':callbackBase64,
        'OSSAccessKeyId': accessid, 
        'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
        'signature': signature,
    };

    up.setOption({
        'url': host,
        'multipart_params': new_multipart_params
    });

    up.start();
}

var uploader = new plupload.Uploader({
	runtimes : 'html5,flash,silverlight,html4',
	browse_button : 'selectfiles', 
    //multi_selection: false,
	container: document.getElementById('container'),
	flash_swf_url : 'lib/plupload-2.1.2/js/Moxie.swf',
	silverlight_xap_url : 'lib/plupload-2.1.2/js/Moxie.xap',
    url : host,
	init: {
		PostInit: function() {
			document.getElementById('ossfile').innerHTML = '';
			document.getElementById('postfiles').onclick = function() {
            set_upload_param(uploader, '', false);
            return false;
			};
		},

		FilesAdded: function(up, files) {
	        if(uploader.files.length>1){ // 最多上传1张图
	            uploader.splice(1,999);
	        }
			plupload.each(files, function(file) {
				document.getElementById('ossfile').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
				+'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
				+'</div>';
			});
		},

		BeforeUpload: function(up, file) {
            check_object_radio();
            get_dirname();
            set_upload_param(up, file.name, true);
        },

		UploadProgress: function(up, file) {
			var d = document.getElementById(file.id);
			d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            var prog = d.getElementsByTagName('div')[0];
			var progBar = prog.getElementsByTagName('div')[0]
			progBar.style.width= 2*file.percent+'px';
			progBar.setAttribute('aria-valuenow', file.percent);
		},

		FileUploaded: function(up, file, info) {
//			writeObj(up);
//			writeObj(file);
//			writeObj(info);
			
            if (info.status == 200){
            	document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = get_uploaded_object_name(file.name)+' 上传成功！';
            	document.getElementById("selectfiles").style.display = "none";
            	document.getElementById("postfiles").style.display = "none";
            	//document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'upload to oss success, object name:' + get_uploaded_object_name(file.name);
            }
            else{
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
            } 
		},

		Error: function(up, err) {
			document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
		}
	}
});

uploader.init();


function writeObj(obj){ 
	 var description = ""; 
	 for(var i in obj){ 
	 var property=obj[i]; 
	 description+=i+" = "+property+"\n"; 
	 } 
	 alert(description); 
	} 
