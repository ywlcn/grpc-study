package com.example.grpc.protodefine;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.21.0)",
    comments = "Source: sunService.proto")
public final class SunServiceGrpc {

  private SunServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.protodefine.SunService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.protodefine.DataType.CommonRequest,
      com.example.grpc.protodefine.DataType.CommonResponse> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = com.example.grpc.protodefine.DataType.CommonRequest.class,
      responseType = com.example.grpc.protodefine.DataType.CommonResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.protodefine.DataType.CommonRequest,
      com.example.grpc.protodefine.DataType.CommonResponse> getSayHelloMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.protodefine.DataType.CommonRequest, com.example.grpc.protodefine.DataType.CommonResponse> getSayHelloMethod;
    if ((getSayHelloMethod = SunServiceGrpc.getSayHelloMethod) == null) {
      synchronized (SunServiceGrpc.class) {
        if ((getSayHelloMethod = SunServiceGrpc.getSayHelloMethod) == null) {
          SunServiceGrpc.getSayHelloMethod = getSayHelloMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.protodefine.DataType.CommonRequest, com.example.grpc.protodefine.DataType.CommonResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.protodefine.SunService", "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.DataType.CommonRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.DataType.CommonResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new SunServiceMethodDescriptorSupplier("SayHello"))
                  .build();
          }
        }
     }
     return getSayHelloMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SunServiceStub newStub(io.grpc.Channel channel) {
    return new SunServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SunServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new SunServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SunServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new SunServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class SunServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void sayHello(com.example.grpc.protodefine.DataType.CommonRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.DataType.CommonResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.protodefine.DataType.CommonRequest,
                com.example.grpc.protodefine.DataType.CommonResponse>(
                  this, METHODID_SAY_HELLO)))
          .build();
    }
  }

  /**
   */
  public static final class SunServiceStub extends io.grpc.stub.AbstractStub<SunServiceStub> {
    private SunServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SunServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SunServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SunServiceStub(channel, callOptions);
    }

    /**
     */
    public void sayHello(com.example.grpc.protodefine.DataType.CommonRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.DataType.CommonResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class SunServiceBlockingStub extends io.grpc.stub.AbstractStub<SunServiceBlockingStub> {
    private SunServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SunServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SunServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SunServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.protodefine.DataType.CommonResponse sayHello(com.example.grpc.protodefine.DataType.CommonRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class SunServiceFutureStub extends io.grpc.stub.AbstractStub<SunServiceFutureStub> {
    private SunServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private SunServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SunServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new SunServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.protodefine.DataType.CommonResponse> sayHello(
        com.example.grpc.protodefine.DataType.CommonRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SunServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SunServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((com.example.grpc.protodefine.DataType.CommonRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.protodefine.DataType.CommonResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SunServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SunServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.protodefine.SunServiceOuterClass.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SunService");
    }
  }

  private static final class SunServiceFileDescriptorSupplier
      extends SunServiceBaseDescriptorSupplier {
    SunServiceFileDescriptorSupplier() {}
  }

  private static final class SunServiceMethodDescriptorSupplier
      extends SunServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SunServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SunServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SunServiceFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .build();
        }
      }
    }
    return result;
  }
}
