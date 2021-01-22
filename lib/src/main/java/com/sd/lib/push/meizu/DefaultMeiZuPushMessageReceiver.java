package com.sd.lib.push.meizu;

import android.content.Context;
import android.content.IntentFilter;

import com.meizu.cloud.pushsdk.MzPushMessageReceiver;
import com.meizu.cloud.pushsdk.platform.message.PushSwitchStatus;
import com.meizu.cloud.pushsdk.platform.message.RegisterStatus;
import com.meizu.cloud.pushsdk.platform.message.SubAliasStatus;
import com.meizu.cloud.pushsdk.platform.message.SubTagsStatus;
import com.meizu.cloud.pushsdk.platform.message.UnRegisterStatus;

public abstract class DefaultMeiZuPushMessageReceiver extends MzPushMessageReceiver
{
    private boolean mHasRegister;

    @Override
    public void onRegisterStatus(Context context, RegisterStatus registerStatus)
    {

    }

    @Override
    public void onUnRegisterStatus(Context context, UnRegisterStatus unRegisterStatus)
    {

    }

    @Override
    public void onPushStatus(Context context, PushSwitchStatus pushSwitchStatus)
    {

    }

    @Override
    public void onSubTagsStatus(Context context, SubTagsStatus subTagsStatus)
    {

    }

    @Override
    public void onSubAliasStatus(Context context, SubAliasStatus subAliasStatus)
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
