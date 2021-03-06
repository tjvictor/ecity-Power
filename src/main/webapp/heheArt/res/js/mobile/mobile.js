function callAjax(url, iTarget, iCallBack, iCallBackParam, iPost, iParams, iLoading) {
    callAjax(url, iTarget, iCallBack, iCallBackParam, iPost, iParams, iLoading, true);
};

function callAjax(url, iTarget, iCallBack, iCallBackParam, iPost, iParams, iLoading, async) {
    var aPost = iPost ? 'POST': 'GET';
    var aParams = iParams ? iParams: '';
    var aTarget = iTarget ? '#' + iTarget: iTarget;
    $(iLoading).css('display', 'block');
    $.ajax({
        async: async,
        type: aPost,
        url: url,
        crossDomain: true,
        data: aParams,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        success: function(data, textStatus, jqXHR) {
            if (aTarget) {
                $(aTarget).html(data);
            }
            if (iCallBack) {
                if (iCallBackParam) {
                    eval(iCallBack)(iCallBackParam, data);
                } else {
                    eval(iCallBack)(data);
                }
            }
        },
        error: function(xhr, textStatus) {
        },
        complete: function(data) {
            $(iLoading).css('display', 'none');
        }
    });
}

function getWxShareConfigCallback(data){
    if(data.status == "ok" && data.callBackData){
        wx.config({
            debug: data.callBackData.debug=="0"?false:true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
            appId: data.callBackData.appId, // 必填，公众号的唯一标识
            timestamp: parseInt(data.callBackData.timestamp), // 必填，生成签名的时间戳
            nonceStr: data.callBackData.nonceStr, // 必填，生成签名的随机串
            signature: data.callBackData.signature,// 必填，签名
            jsApiList: data.callBackData.jsApiList // 必填，需要使用的JS接口列表
        });

        wx.ready(function () {   //需在用户可能点击分享按钮前就先调用
            //自定义“分享给朋友”及“分享到QQ”按钮的分享内容（1.4.0）
            wx.updateAppMessageShareData({
                title: '禾禾美学馆', // 分享标题
                desc: '我们认为，学习艺术不只是培养艺术家，而是让每个孩子能通过艺术的启发，提高创意能力，找到自己的价值、发现独特的自己，让艺术融入生活', // 分享描述
                link: 'http://www.ecity-power.com/heheArt/index.html', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                imgUrl: 'http://www.ecity-power.com/heheArt/res/img/teacher/liubo.png', // 分享图标
            }, function(res) {
                //这里是回调函数
            });

            //自定义“分享到朋友圈”及“分享到QQ空间”按钮的分享内容（1.4.0）
            wx.updateTimelineShareData({
                    title: '禾禾美学馆', // 分享标题
                    link: 'http://www.ecity-power.com/heheArt/index.html', // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
                    imgUrl: 'http://www.ecity-power.com/heheArt/res/img/teacher/liubo.png', // 分享图标
            }, function(res) {
                //这里是回调函数
            });
        });
    }
}

$('.hehe-login-button').on('click', function(){
    var name = $('.hehe-login-username').val();
    var pwd = $('.hehe-login-password').val();
    if($.trim(name) === ''){
        $.alert("手机不能为空！", "警告！");
    }
    if($.trim(pwd) === ''){
        $.alert("密码不能为空！", "警告！");
    }
    var param = "tel="+name+"&password="+pwd;

    callAjax('/heheArtService/login', '', 'loginCallback', '', '', param, '');
});
function loginCallback(data){
    if (data.status == "ok" && data.callBackData){
        if(data.callBackData.outOfDate){
            $.alert("会员已经过期！！！","提示");
            return;
        }
        Cookies.set("meet-hehe-user", data.callBackData, { expires: 1 });
        $.alert("登录成功！！！","提示");
        $('#tabbar .weui-tab__bd-item--content').load('view/home.html');
        close_login();
    }else{
        $.alert("手机号码不存在或是密码不正确！请重新登录", "警告！");
    }
}
function open_login(){
    $('.hehe-login-username').val('');
    $('.hehe-login-password').val('');
    $('#loginBar').addClass('weui-tab__bd-item--active');
    $('#tabbar').removeClass('weui-tab__bd-item--active');
}
function close_login(){
    $('#tabbar').addClass('weui-tab__bd-item--active');
    $('#loginBar').removeClass('weui-tab__bd-item--active');
}

function checkUser(){
    var user = Cookies.get('meet-hehe-user');
    if(!user){
        open_login();
        return false;
    }
    return $.parseJSON(user);
}

function logout(){
    Cookies.remove("meet-hehe-user");
    $.alert("注销成功！！！","提示");
    checkUser();
}