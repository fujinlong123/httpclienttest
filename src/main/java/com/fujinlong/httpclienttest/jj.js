
!
function() {
    window.onerror = function(n, o, e) {
        var t = "https:" == location.protocol ? "https://ssl.qq.com/ptlogin/cgi-bin/ptlogin_report?": "http://log.wtlogin.qq.com/cgi-bin/ptlogin_report?",
        _ = document.createElement("img"),
        i = encodeURIComponent(n + "|_|" + o + "|_|" + e + "|_|" + window.navigator.userAgent);
        _.src = t + "id=195279&msg=" + i + "&v=" + Math.random()
    }
} ();
var g_cdn_js_fail = !1,
pt = {};
pt.str = {
    no_uin: "您还没有输入帐号！",
    no_pwd: "您还没有输入密码！",
    no_vcode: "您还没有输入验证码！",
    inv_uin: "请输入正确的帐号！",
    inv_vcode: "请输入完整的验证码！",
    qlogin_expire: "您所选择号码对应的QQ已经失效，请检查该号码对应的QQ是否已经被关闭。",
    other_login: "帐号登录",
    h_pt_login: "帐号密码登录",
    otherqq_login: "QQ帐号密码登录"
},
pt.ptui = {
    s_url: "https\x3A\x2F\x2Fmail.qq.com\x2Fcgi-bin\x2Flogin\x3Fvt\x3Dpassport\x26vm\x3Dwpt\x26ft\x3Dloginpage\x26target\x3D",
    proxy_url: "https\x3A\x2F\x2Fmail.qq.com\x2Fproxy.html",
    jumpname: encodeURIComponent(""),
    mibao_css: encodeURIComponent(""),
    defaultUin: "",
    lockuin: parseInt("0"),
    href: "https\x3A\x2F\x2Fxui.ptlogin2.qq.com\x2Fcgi-bin\x2Fxlogin\x3Fappid\x3D522005705\x26daid\x3D4\x26s_url\x3Dhttps\x3A\x2F\x2Fmail.qq.com\x2Fcgi-bin\x2Flogin\x3Fvt\x3Dpassport\x2526vm\x3Dwpt\x2526ft\x3Dloginpage\x2526target\x3D\x26style\x3D25\x26low_login\x3D1\x26proxy_url\x3Dhttps\x3A\x2F\x2Fmail.qq.com\x2Fproxy.html\x26need_qr\x3D0\x26hide_border\x3D1\x26border_radius\x3D0\x26self_regurl\x3Dhttp\x3A\x2F\x2Fzc.qq.com\x2Fchs\x2Findex.html\x3Ftype\x3D1\x26app_id\x3D11005\x3Ft\x3Dregist\x26pt_feedback_link\x3Dhttp\x3A\x2F\x2Fsupport.qq.com\x2Fdiscuss\x2F350_1.shtml\x26css\x3Dhttps\x3A\x2F\x2Fres.mail.qq.com\x2Fzh_CN\x2Fhtmledition\x2Fstyle\x2Fptlogin_input24e6b9.css",
    login_sig: "",
    clientip: "",
    serverip: "",
    version: "201203081004",
    ptui_version: encodeURIComponent("10166"),
    isHttps: !1,
    cssPath: "https://ui.ptlogin2.qq.com/style.ssl/25",
    domain: encodeURIComponent("qq.com"),
    fromStyle: parseInt(""),
    pt_3rd_aid: encodeURIComponent("0"),
    appid: encodeURIComponent("522005705"),
    lang: encodeURIComponent("2052"),
    style: encodeURIComponent("25"),
    low_login: encodeURIComponent("1"),
    daid: encodeURIComponent("4"),
    regmaster: encodeURIComponent(""),
    enable_qlogin: "1",
    noAuth: "0",
    target: isNaN(parseInt("1")) ? {
        _top: 1,
        _self: 0,
        _parent: 2
    } ["1"] : parseInt("1"),
    csimc: encodeURIComponent("0"),
    csnum: encodeURIComponent("0"),
    authid: encodeURIComponent("0"),
    auth_mode: encodeURIComponent("0"),
    pt_qzone_sig: "0",
    pt_light: "0",
    pt_vcode_v1: "0",
    pt_ver_md5: "000D9E475C0F4394F5BAF2CC17BFBB6AAAAE963BAA51DC6D7D1C6391",
    gzipEnable: "1"
};




function cleanCache(e) {
    var t = document.createElement("iframe");
    3 == e.split("#").length && (e = e.substring(0, e.lastIndexOf("#"))),
    t.src = e,
    t.style.display = "none",
    document.body.appendChild(t)
}
function loadScript(e, t, n) {
    var o = document.createElement("script");
    o.type = "text/javascript",
    o.charset = "utf-8",
    o.onload = o.onerror = o.onreadystatechange = function() {
        return window[n] ? void(loadJs.onloadTime = +new Date) : void(this.readyState && ("loaded" !== this.readyState && "complete" !== this.readyState || window[n]) || (t && t(), o.onerror = o.onreadystatechange = null))
    },
    o.src = e,
    document.getElementsByTagName("head")[0].appendChild(o)
}
function ptuiV(e) {
    e != window.pt.ptui.ptui_version && cleanCache("/clearcache.html#" + location.href)
}
function checkVersion() {
    var e = document.createElement("script");
    e.src = "/ptui_ver.js?v=" + Math.random() + "&ptui_identifier=" + pt.ptui.pt_ver_md5,
    document.body.appendChild(e)
}
function loadJs() {
    1 != loadJs.hasLoad && (loadJs.hasLoad = !0, loadScript("../js/10166/c_login_2.js?max_age=604800&ptui_identifier=000E0124A4176A1F0987B0DF50DEA5AF4DB40A01B8EB802B9E0A122D0A",
    function() {
        window.g_cdn_js_fail = !0;
        var e = new Image;
        e.src = location.protocol + "//ui.ptlogin2.qq.com/cgi-bin/report?id=242325&union=256043";
        var t = "../js/" + pt.ptui.ptui_version + "/c_login_2.js?max_age=604800&ptui_identifier=000E0124A4176A1F0987B0DF50DEA5AF4DB40A01B8EB802B9E0A122D0A";
        loadScript(t,
        function() {
            var t = document.getElementById("login_button");
            t && (t.disabled = !0),
            e.src = location.protocol + "//ui.ptlogin2.qq.com/cgi-bin/report?id=280504"
        },
        "ptuiCB")
    },
    "ptuiCB"), ready())
}
function ready() {
    window.setTimeout(checkVersion, 1500)
}
document.addEventListener && document.addEventListener("DOMContentLoaded", loadJs),
window.onload = loadJs,
window.setTimeout(loadJs, 5e3); < /script>/




function cleanCache(e) {
    var t = document.createElement("iframe");
    3 == e.split("#").length && (e = e.substring(0, e.lastIndexOf("#"))),
    t.src = e,
    t.style.display = "none",
    document.body.appendChild(t)
}
function loadScript(e, t, n) {
    var o = document.createElement("script");
    o.type = "text/javascript",
    o.charset = "utf-8",
    o.onload = o.onerror = o.onreadystatechange = function() {
        return window[n] ? void(loadJs.onloadTime = +new Date) : void(this.readyState && ("loaded" !== this.readyState && "complete" !== this.readyState || window[n]) || (t && t(), o.onerror = o.onreadystatechange = null))
    },
    o.src = e,
    document.getElementsByTagName("head")[0].appendChild(o)
}
function ptuiV(e) {
    e != window.pt.ptui.ptui_version && cleanCache("/clearcache.html#" + location.href)
}
function checkVersion() {
    var e = document.createElement("script");
    e.src = "/ptui_ver.js?v=" + Math.random() + "&ptui_identifier=" + pt.ptui.pt_ver_md5,
    document.body.appendChild(e)
}
function loadJs() {
    1 != loadJs.hasLoad && (loadJs.hasLoad = !0, loadScript("../js/10167/c_login_2.js?max_age=604800&ptui_identifier=000DA76410FB2C55D84BB10A3F96DE0E62081FCB316D17EB9EB1F35B",
    function() {
        window.g_cdn_js_fail = !0;
        var e = new Image;
        e.src = location.protocol + "//ui.ptlogin2.qq.com/cgi-bin/report?id=242325&union=256043";
        var t = "../js/" + pt.ptui.ptui_version + "/c_login_2.js?max_age=604800&ptui_identifier=000DA76410FB2C55D84BB10A3F96DE0E62081FCB316D17EB9EB1F35B";
        loadScript(t,
        function() {
            var t = document.getElementById("login_button");
            t && (t.disabled = !0),
            e.src = location.protocol + "//ui.ptlogin2.qq.com/cgi-bin/report?id=280504"
        },
        "ptuiCB")
    },
    "ptuiCB"), ready())
}
function ready() {
    window.setTimeout(checkVersion, 1500)
}
document.addEventListener && document.addEventListener("DOMContentLoaded", loadJs),
window.onload = loadJs,
window.setTimeout(loadJs, 5e3);