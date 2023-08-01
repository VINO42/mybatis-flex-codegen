package io.github.vino42.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.github.vino42.exception.SystemInternalException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

import static cn.hutool.core.date.DatePattern.NORM_DATETIME_MS_PATTERN;
import static io.github.vino42.common.ServiceResponseCodeEnum.*;

/**
* 服务统一包装类
*
* @param <T>
    */
    @Slf4j
    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public final class ServiceResponseResult<T> implements Serializable {

        /**
        * 开始时间
        */
        @DateTimeFormat(pattern = NORM_DATETIME_MS_PATTERN)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = NORM_DATETIME_MS_PATTERN)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime startTime;

        /**
        * 结束时间
        */
        @DateTimeFormat(pattern = NORM_DATETIME_MS_PATTERN)
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = NORM_DATETIME_MS_PATTERN)
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        @JsonSerialize(using = LocalDateTimeSerializer.class)
        private LocalDateTime endTime;

        /**
        * 编号.
        */
        private int status;

        /**
        * 信息.
        */
        private String message;

        /**
        * 结果数据
        */
        private T data;


        /**
        * traceId
        */
        private String requestId;

        private String path;

        /**
        * 异常堆栈
        */
        private Error error;

        public ServiceResponseResult() {

        this.startTime = LocalDateTime.now();
        }

        /**
        * Instantiates a new wrapper.
        *
        * @param status  the code
        * @param message the message
        */
        public ServiceResponseResult(int status, String message) {

        this(status, message, null);
        }

        /**
        * Instantiates a new wrapper.
        *
        * @param status  the code
        * @param message the message
        * @param data    the result
        */
        public ServiceResponseResult(int status, String message, T data) {

        super();
        this.startTime = LocalDateTime.now();
        this.status(status).message(message).data(data);
        }

        public ServiceResponseResult(int status, String message, T data, Error error) {
        super();
        this.startTime = LocalDateTime.now();
        this.status(status).message(message).data(data);
        this.error = error;
        }

        public ServiceResponseResult<T> stackTrace(StackTraceElement[] stackTrace) {
            this.error = new Error();
            this.error.setStackTrace(stackTrace);
            return this;
            }

            public ServiceResponseResult<T> exception(Exception e) {
                this.error = new Error();
                this.error.setStackTrace(e.getStackTrace());
                this.error.setDetail(e.getLocalizedMessage());
                this.error.setMessage(e.getMessage());
                return this;
                }

                public ServiceResponseResult<T> throwable(Throwable throwable) {
                    this.error = new Error();
                    this.error.setStackTrace(throwable.getStackTrace());
                    this.error.setDetail(throwable.getLocalizedMessage());
                    this.error.setMessage(throwable.getMessage());
                    return this;
                    }

                    public ServiceResponseResult<T> detail(String detail) {
                        if (this.error == null) {
                        this.error = new Error();
                        }
                        this.error.setDetail(detail);
                        return this;
                        }

                        /**
                        * Sets the 编号 , 返回自身的引用.
                        *
                        * @param code the new 编号
                        * @return the wrapper
                        */
                        public ServiceResponseResult<T> status(int code) {

                            this.setStatus(code);
                            return this;
                            }

                            /**
                            * Sets the 信息 , 返回自身的引用.
                            *
                            * @param message the new 信息
                            * @return the wrapper
                            */
                            public ServiceResponseResult<T> message(String message) {

                                this.setMessage(message);
                                return this;
                                }

                                public ServiceResponseResult<T> path(String path) {
                                    this.setPath(path);
                                    return this;
                                    }

                                    public String getPath() {
                                    return path;
                                    }

                                    public void setPath(String path) {
                                    this.path = path;
                                    }

                                    /**
                                    * Sets the 结果数据 , 返回自身的引用.
                                    *
                                    * @param data the new 结果数据
                                    * @return the wrapper
                                    */
                                    public ServiceResponseResult<T> data(T data) {

                                        this.setData(data);
                                        return this;
                                        }

                                        /**
                                        * 判断是否成功： 依据 Wrapper.SUCCESS_CODE == this.code
                                        *
                                        * @return code =200,true;否则 false.
                                        */
                                        public boolean success() {

                                        return SUCCESS.status == this.status;
                                        }

                                        public ServiceResponseResult<T> validation(String message, String code, String field) {
                                            this.error = new Error();
                                            this.error.setMessage(message);
                                            this.error.setCode(code);
                                            this.error.setField(field);

                                            this.status(ILLEGAL_ARGS.status).message(message);
                                            return this;
                                            }

                                            /**
                                            * 判断是否成功： 依据 Wrapper.SUCCESS_CODE != this.code
                                            *
                                            * @return code !=200,true;否则 false.
                                            */
                                            private boolean error() {

                                            return !success();
                                            }

                                            /**
                                            * 判断是否成功： 依据 Wrapper.SUCCESS_CODE != this.code
                                            *
                                            * @return code !=0,true;否则 false.
                                            */

                                            private boolean isSuccessThrow() {
                                            if (this.status != SUCCESS.status) {
                                            throw new SystemInternalException(RPC_ERROR);
                                            }
                                            return true;
                                            }

                                            /**
                                            * 判断是否成功： 依据 Wrapper.SUCCESS_CODE != this.code
                                            *
                                            * @return code !=0,true;否则 false.
                                            */

                                            public boolean isSuccess() {
                                            boolean isSuc = this.status == SUCCESS.status;
                                            if (!isSuc) {
                                            log.error("[feignClient remote call error:{}]", this);
                                            }
                                            return isSuc;
                                            }

                                            /**
                                            * 判断获取数据
                                            *
                                            * @return code !=0,true;否则 false.
                                            */
                                            private T getFeignData() {
                                            if (this.status != SUCCESS.status) {
                                            log.error("[feignClient remote call error:{}]", this);
                                            throw new SystemInternalException(RPC_UNKNOWN);
                                            }
                                            return this.getData();
                                            }
                                            }
