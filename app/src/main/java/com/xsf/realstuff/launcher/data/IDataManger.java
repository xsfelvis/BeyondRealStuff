package com.xsf.realstuff.launcher.data;

import com.xsf.realstuff.launcher.data.database.IDbHelper;
import com.xsf.realstuff.launcher.data.network.IApiHelper;
import com.xsf.realstuff.launcher.data.preference.ISharePreferenceHelper;

/**
 * Author: xushangfei
 * Time: created at 2017/11/6.
 * Description:
 */

public interface IDataManger extends IApiHelper, IDbHelper, ISharePreferenceHelper {

}
