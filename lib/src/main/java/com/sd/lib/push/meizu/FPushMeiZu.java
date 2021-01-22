package com.sd.lib.push.meizu;

import android.content.Context;

import com.meizu.cloud.pushsdk.PushManager;

public class FPushMeiZu
{
    private FPushMeiZu()
    {
    }

    /**
     * 初始化
     *
     * @param appid
     * @param appkey
     */
    public static void init(String appid, String appkey, Context context)
    {
        PushManager.register(context, appid, appkey);
    }
}
