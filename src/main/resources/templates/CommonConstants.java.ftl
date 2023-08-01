package path.objs;

public interface CommonConstants {

String SUCCESS_MSG = "success";
/** 成功码. */
public static final int SUCCESS_CODE = 0;

public static final int SERVER_ERROR_CODE = 500;

public static final String SERVER_ERROR_MSG = "系统内部异常";

/** 没有权限错误码 */
public static final int ACCESS_DENIED_ERROR_CODE = 401;

/** 限流错误码 */
public static final int LIMIT_ERROR_CODE = 403;

/** 限流提示语 */
public static final String LIMIT_ERROR_MSG = "请稍后重试";

/** 降级错误码 */
public static final int DEGRADE_ERROR_CODE = 501;

/** 降级提示语 */
public static final String DEGRADE_ERROR_MSG = "服务暂时不可用,请稍后重试";

/** 成功码. */
public static final int SUCCESS_CODE = 0;

/** 成功信息. */
public static final String SUCCESS_MESSAGE = "操作成功";

/** 错误码：参数非法 */
public static final int ILLEGAL_ARGS_CODE_ = 400;

/** 错误信息：参数非法 */
public static final String ILLEGAL_ARGS_MESSAGE = "参数非法";

}
