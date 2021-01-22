package com.sd.lib.push.meizu;

import android.content.Context;
import android.content.IntentFilter;

import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.handler.MzPushMessage;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

public abstract class DefaultMeiZuPushMessageReceiver extends MzPushMessageReceiver
{
    private boolean mHasRegister;

    /**
     * 调用订阅方法后，会在此方法回调结果
     * 订阅方法：PushManager.register(context, appId, appKey)
     *
     * @param context
     * @param registerStatus
     */
    @Override
    public void onRegisterStatus(Context context, RegisterStatus registerStatus)
    {
    }

    /**
     * 调用取消订阅方法后，会在此方法回调结果
     * 取消订阅方法：PushManager.unRegister(context, appId, appKey)
     *
     * @param context
     * @param unRegisterStatus
     */
    @Override
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus)
    {
    }

    /**
     * 调用开关转换或检查开关状态方法后，会在此方法回调开关状态
     * 通知栏开关转换方法：PushManager.switchPush(context, appId, appKey, pushId, pushType, switcher)
     * 检查开关状态方法：PushManager.checkPush(context, appId, appKey, pushId)
     *
     * @param context
     * @param pushSwitchStatus
     */
    @Override
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus)
    {
    }

    /**
     * 调用标签订阅、取消标签订阅、取消所有标签订阅和获取标签列表方法后，会在此方法回调标签相关信息
     * 标签订阅方法：PushManager.subScribeTags(context, appId, appKey, pushId, tags)
     * 取消标签订阅方法：PushManager.unSubScribeTags(context, appId, appKey, pushId,tags)
     * 取消所有标签订阅方法：PushManager.unSubScribeAllTags(context, appId, appKey, pushId)
     * 获取标签列表方法：PushManager.checkSubScribeTags(context, appId, appKey, pushId)
     *
     * @param context
     * @param subTagsStatus
     */
    @Override
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus)
    {
    }

    /**
     * 调用别名订阅、取消别名订阅和获取别名方法后，会在此方法回调别名相关信息
     * 别名订阅方法：PushManager.subScribeAlias(context, appId, appKey, pushId, alias)
     * 取消别名订阅方法：PushManager.unSubScribeAlias(context, appId, appKey, pushId, alias)
     * 获取别名方法：PushManager.checkSubScribeAlias(context, appId, appKey, pushId)
     *
     * @param context
     * @param subAliasStatus
     */
    @Override
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus)
    {
    }

    /**
     * 当用户点击通知栏消息后会在此方法回调
     *
     * @param context
     * @param mzPushMessage
     */
    @Override
    public void onNotificationClicked(Context context, MzPushMessage mzPushMessage)
    {
    }

    /**
     * 当推送的通知栏消息展示后且应用进程存在时会在此方法回调
     *
     * @param context
     * @param mzPushMessage
     */
    @Override
    public void onNotificationArrived(Context context, MzPushMessage mzPushMessage)
    {
    }

    /**
     * 注册广播
     *
     * @param context
     */
    public synchronized void register(Context context)
    {
        if (mHasRegister)
            return;

        final IntentFilter filter = new IntentFilter();
        filter.addAction("com.meizu.flyme.push.intent.MESSAGE");
        filter.addAction("com.meizu.flyme.push.intent.REGISTER.FEEDBACK");
        filter.addAction("com.meizu.flyme.push.intent.UNREGISTER.FEEDBACK");
        filter.addAction("com.meizu.c2dm.intent.REGISTRATION");
        filter.addAction("com.meizu.c2dm.intent.RECEIVE");
        filter.addCategory(context.getPackageName());
        context.registerReceiver(this, filter);
        mHasRegister = true;
    }

    /**
     * 取消注册
     *
     * @param context
     */
    public synchronized void unregister(Context context)
    {
        if (mHasRegister)
        {
            mHasRegister = false;
            context.unregisterReceiver(this);
        }
    }
}
