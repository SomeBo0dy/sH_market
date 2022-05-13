package pers.xyj.enums;

public enum AppHttpCodeEnum {
    // 成功
    SUCCESS(200,"操作成功"),
    // 登录
    NEED_LOGIN(401,"需要登录后操作"),
    NO_OPERATOR_AUTH(403,"无权限操作"),
    SYSTEM_ERROR(500,"出现错误"),
    USERNAME_EXIST(501,"用户名已存在"),
    PHONENUMBER_EXIST(502,"手机号已存在"), EMAIL_EXIST(503, "邮箱已存在"),
    REQUIRE_USERNAME(504, "必需填写用户名"),
    LOGIN_ERROR(505,"用户名或密码错误"),
    USERNAME_NOT_NULL(506,"用户名不得为空" ),
    NICKNAME_NOT_NULL(507,"昵称不得为空" ),
    PASSWORD_NOT_NULL(508,"密码不得为空" ),
    EMAIL_NOT_NULL(509,"邮箱不得为空" ),
    GOOD_TITLE_NOT_NULL(510,"商品标题不得为空" ),
    GOOD_NAME_NOT_NULL(511,"商品名称不得为空" ),
    GOOD_CATE_NOT_NULL(512,"商品类型不得为空" ),
    GOOD_PIC_URL_NOT_NULL(513,"商品缩略图不得为空" ),
    GOOD_PRICE_NOT_NULL(514,"商品价格不得为空" ),

    FILE_TYPE_ERROR(515,"请上传png文件" ),
    TYPE_ERROR(516,"用户类型错误" ),
    GOOD_DELETE_NULL(517,"该商品不存在" );
    int code;
    String msg;

    AppHttpCodeEnum(int code, String errorMessage){
        this.code = code;
        this.msg = errorMessage;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
