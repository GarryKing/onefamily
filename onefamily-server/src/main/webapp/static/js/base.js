/**
 * Created by GarryKing on 2016/12/3.
 */
//===============================
// ��������
//===============================
function commAjaxByGET(url, param, callback, errorCallBack, headerParam) {
    jQuery.ajax({
        url: url,
        dataType: 'json',
        type: "GET",
        data: param,
        headers: headerParam,
        success: callback,
        error: function (data) {
            console.log("ִ�� " + url + " ������...");
            if (errorCallBack) {
                errorCallBack(data);
            }
        }
    });
}

function commAjaxByPost(url, param, callback, errorCallBack, headerParam) {
    jQuery.ajax({
        url: url,
        dataType: 'json',
        type: "POST",
        data: param,
        headers: headerParam,
        success: callback,
        error: function (data) {
            console.log("ִ�� " + url + " ������...");
            if (errorCallBack) {
                errorCallBack(data);
            }
        }
    })
    ;
}

function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //����һ������Ŀ�������������ʽ����
    var r = window.location.search.substr(1).match(reg);  //ƥ��Ŀ�����

    // ���뷵��
    if (r != null) return decodeURI(r[2]);
    return null; //���ز���ֵ
}

function loadURL(url) {
    var iFrame;
    iFrame = document.createElement("iframe");
    iFrame.setAttribute("src", url);
    iFrame.setAttribute("style", "display:none;");
    iFrame.setAttribute("height", "0px");
    iFrame.setAttribute("width", "0px");
    iFrame.setAttribute("frameborder", "0");
    document.body.appendChild(iFrame);
    iFrame.parentNode.removeChild(iFrame);
    iFrame = null;
}

function redirectUrl(url) {
    window.location.href = url;
}

function checkMobile() {
    var flag = false;
    var agent = navigator.userAgent.toLowerCase();
    var keywords = ["android", "iphone", "ipod", "ipad", "windows phone", "mqqbrowser", "KEDIRF-APP"];

    //�ų� Windows ����ϵͳ
    if (!(agent.indexOf("windows nt") > -1) || (agent.indexOf("windows nt") > -1 && agent.indexOf("compatible; msie 9.0;") > -1)) {
        //�ų�ƻ������ϵͳ
        if (!(agent.indexOf("windows nt") > -1) && !agent.indexOf("macintosh") > -1) {
            for (var item in keywords) {
                if (agent.indexOf(item) > -1) {
                    flag = true;
                    break;
                }
            }
        }
    }
    return flag;
}