package com.xxy.util;

public class SMSUtil {

    // 现在由于只有注册过的项目才能使用短信功能，这里做了简化
    public static boolean send(String phoneNumber,String code) {
        System.out.println("已向" + phoneNumber + "发送取件码：" + code);
        return true;
    }

//    public static boolean send(String phoneNumber,String code) {
//        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4GJVun7NhPS8FMgSqqS4", "TgTLk8KVJjESFu6iC1jTHsVMK53z5Q");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CommonRequest request = new CommonRequest();
//        request.setSysMethod(MethodType.POST);
//        request.setSysDomain("dysmsapi.aliyuncs.com");
//        request.setSysVersion("2017-05-25");
//        request.setSysAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", phoneNumber);
//        request.putQueryParameter("SignName", "快件e栈");
//        request.putQueryParameter("TemplateCode", "SMS_196641606");
//        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\"}");
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//            String json = response.getData();
//            Gson g = new Gson();
//            HashMap result = g.fromJson(json, HashMap.class);
//            if("OK".equals(result.get("Message"))) {
//                return true;
//            }else{
//                System.out.println("短信发送失败，原因："+result.get("Message"));
//            }
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
}
