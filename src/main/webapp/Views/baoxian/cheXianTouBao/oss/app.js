
//具体业务url地址
var appServer = "";
//对应的shopcode
var shopCode = "";
//对应的orderCode
var orderCode = "";
//业务json需要替换的信息
//var DuduOssCallbackVarData = {};
//全局变量上传变量
var upcount=0;
var upcountMAX=99999999;

//oss链接参数 临时授权
var urllib = OSS.urllib;
var Buffer = OSS.Buffer;
var OSS = OSS.Wrapper;
var STS = OSS.STS;
//oss 接收信息
var DuduOssCallback = "";
var DuduOssCallbackVar = "";
var DuduParamsList = "";
var DuduOrderTag = "";
var DuduBasePath = "";
var DuduFileKey = "";
var DuduParams = "";

//new
var applyTokenDoNew = function (fileObj,DuduOssCallbackVarData) {
    var url = "http://asl.dev.duduchewang.cn/oss/ossconfig/CS00001/18";
    return urllib.request(url, {
        method: 'GET'
    }).then(function (result) {
        console.log("=========================>>"+result);
        var creds = JSON.parse(result.data);
        DuduOssCallback = creds.xOssCallBack;
        DuduParamsList = creds.paramsList;
        DuduOrderTag = creds.orderTag;
        DuduBasePath = creds.basePath;

        var client = new OSS({
            region: creds.region,
            accessKeyId: creds.accessKeyId,
            accessKeySecret: creds.accessKeySecret,
            stsToken: creds.securityToken,
            bucket: creds.bucketName,
        });


        substitutionpara(DuduOssCallbackVarData,fileObj.name);
        DuduOssCallbackVarData={};

        var file = fileObj;
        client.multipartUpload(DuduFileKey, file, {
//			progress: progress,
            headers: {
                "x-oss-callback" : DuduOssCallback,
                "x-oss-callback-var" : DuduOssCallbackVar
            }
        }).then(function (res) {
            console.log('upload success: %j', res);
//			return retre(res.res.status);
        })
            .then(function () {
                // upcount++;
//			alert(upcount)
            })
        ;
    });
};



//通过appServer 请求授权 以及业务参数 callback信息
var applyTokenDo = function (func) {
    var url = appServer;
    return urllib.request(url, {
        method: 'GET'
    })
        .then(function (result) {
            var creds = JSON.parse(result.data);
            DuduOssCallback = creds.xOssCallBack;
            DuduParamsList = creds.paramsList;
            DuduOrderTag = creds.orderTag;
            DuduBasePath = creds.basePath;

            var client = new OSS({
                region: creds.region,
                accessKeyId: creds.accessKeyId,
                accessKeySecret: creds.accessKeySecret,
                stsToken: creds.securityToken,
                bucket: creds.bucketName,
            });
            return func(client);
        })
        ;
};

//页面的fileObject 并验证信息 作为参数传递
var uploadFile = function (client) {
    var file = document.getElementById('file').files[0];
    substitutionpara(DuduOssCallbackVarData,file.name);
    //控制台打印 文件名称 存储路径
//  console.log(file.name + ' => ' + DuduFileKey);
    return client.multipartUpload(DuduFileKey, file, {
//	  progresss: progresss
        headers: {
            "x-oss-callback" : DuduOssCallback,
            "x-oss-callback-var" : DuduOssCallbackVar
        }
    }).then(function (res) {
        //console.log('upload success: %j', res);
        console.log(' success file1 ');
//	    return listFiles(client);
    });
};

//替换业务参数
function substitutionpara(params,filename) {
    var timestamp=new Date().getTime();
    for ( var i = 0;i< DuduParamsList.length;i++) {
        var keya = DuduParamsList[i];
        DuduBasePath = DuduBasePath.replace("{" + keya + "}",params[''+keya+'']);
        DuduOrderTag = DuduOrderTag.replace("{" + keya + "}", params[''+keya+'']);
    }
    var DuduOssCallbackVarData = {};
    for(var keyb in params) {
        DuduOssCallbackVarData["x:"+keyb] = params[''+keyb+''];
    }
    DuduOssCallbackVar = Base64.encode(JSON.stringify(DuduOssCallbackVarData));
    DuduFileKey = DuduBasePath + DuduOrderTag + timestamp + suffix(filename);
}

//自定义的fileObject
var uploadFile2 = function (client) {
    var file = document.getElementById('file1').files[0];
    substitutionpara(DuduOssCallbackVarData,file.name);
    console.log(file.name + ' => ' + DuduFileKey);
    return client.multipartUpload(DuduFileKey, file, {
        headers: {
            "x-oss-callback" : DuduOssCallback,
            "x-oss-callback-var" : DuduOssCallbackVar
        }
    });
};
//自定义的fileObject
var uploadFile3 = function (client) {
    var file = document.getElementById('file2').files[0];
    substitutionpara(DuduOssCallbackVarData,file.name);
    console.log(file.name + ' => ' + DuduFileKey);
    return client.multipartUpload(DuduFileKey, file, {
        headers: {
            "x-oss-callback" : DuduOssCallback,
            "x-oss-callback-var" : DuduOssCallbackVar
        }
    });
};
//自定义的fileObject
var uploadFile4 = function (client) {
    var file = document.getElementById('file3').files[0];
    substitutionpara(DuduOssCallbackVarData,file.name);
    console.log(file.name + ' => ' + DuduFileKey);
    return client.multipartUpload(DuduFileKey, file, {
        headers: {
            "x-oss-callback" : DuduOssCallback,
            "x-oss-callback-var" : DuduOssCallbackVar
        }
    });
};


//file onchange
//判断文件类型  照片大小
function getPhotoSize(obj){
    //图片预览
    //setImagePreview();

    if(obj.value==null || obj.value=='' || obj.value==undefined){
        alert("请选择对应的图片！");
        return false;
    }
    photoExt=obj.value.substr(obj.value.lastIndexOf(".")).toLowerCase();//获得文件后缀名
    var tp =".jpg,.gif,.bmp,.JPG,.GIF,.BMP,.ico,.png";
    var rs=tp.indexOf(photoExt);
    //如果返回的结果大于或等于0，说明包含允许上传的文件类型
    if(rs>=0){
        //return true;
    }else{
        alert("您选择的上传文件不是有效的图片文件！");
        return false;
    }
    var fileSize = 0;
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;
    if (isIE && !obj.files) {
        var filePath = obj.value;
        var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
        var file = fileSystem.GetFile (filePath);
        fileSize = file.Size;
    }else {
        fileSize = obj.files[0].size;
    }
    fileSize=Math.round(fileSize/1024*100)/100; //单位为KB
    if(fileSize>=500){
//  if(fileSize>=10){
        alert("照片最大尺寸为500KB，请重新上传!");
        return false;
    }
    return true;
}

//打印Object对象信息
function writeObj(obj){
    var description = "";
    for(var i in obj){
        var property=obj[i];
        description+=i+" = "+property+"\n";
    }
    alert(description);
}
//取得文件后缀名
function suffix(file_name){
    var result =/\.[^\.]+/.exec(file_name);
    return result;
}

//进度条实例
var progress = function (p) {
    return function (done) {
        var bar = document.getElementById('progress-bar');
        bar.style.width = Math.floor(p * 100) + '%';
        bar.innerHTML = Math.floor(p * 100) + '%';
        done();
    }
};
//绑定事件实例
//window.onload = function () {
//	document.getElementById('file-button').onclick = function () {
//			applyTokenDo(uploadFile);
//		}
//};

//定时刷新 关闭页面
window.setInterval(test,2000);
function test() {
    if(upcount>=upcountMAX){
        api.close();
    }
}
// 关闭页面 upcount++
function jiaCount() {
    upcount++;
}

