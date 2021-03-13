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
    comments = "Source: helloworld.proto")
public final class HelloWorldGrpc {

  private HelloWorldGrpc() {}

  public static final String SERVICE_NAME = "com.example.grpc.protodefine.HelloWorld";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest,
      com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHello",
      requestType = com.example.grpc.protodefine.Helloworld.HelloRequest.class,
      responseType = com.example.grpc.protodefine.Helloworld.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest,
      com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest, com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloMethod;
    if ((getSayHelloMethod = HelloWorldGrpc.getSayHelloMethod) == null) {
      synchronized (HelloWorldGrpc.class) {
        if ((getSayHelloMethod = HelloWorldGrpc.getSayHelloMethod) == null) {
          HelloWorldGrpc.getSayHelloMethod = getSayHelloMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.protodefine.Helloworld.HelloRequest, com.example.grpc.protodefine.Helloworld.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.protodefine.HelloWorld", "SayHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.Helloworld.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.Helloworld.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloWorldMethodDescriptorSupplier("SayHello"))
                  .build();
          }
        }
     }
     return getSayHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest,
      com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloServerStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHelloServerStreaming",
      requestType = com.example.grpc.protodefine.Helloworld.HelloRequest.class,
      responseType = com.example.grpc.protodefine.Helloworld.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest,
      com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloServerStreamingMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest, com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloServerStreamingMethod;
    if ((getSayHelloServerStreamingMethod = HelloWorldGrpc.getSayHelloServerStreamingMethod) == null) {
      synchronized (HelloWorldGrpc.class) {
        if ((getSayHelloServerStreamingMethod = HelloWorldGrpc.getSayHelloServerStreamingMethod) == null) {
          HelloWorldGrpc.getSayHelloServerStreamingMethod = getSayHelloServerStreamingMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.protodefine.Helloworld.HelloRequest, com.example.grpc.protodefine.Helloworld.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.protodefine.HelloWorld", "SayHelloServerStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.Helloworld.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.Helloworld.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloWorldMethodDescriptorSupplier("SayHelloServerStreaming"))
                  .build();
          }
        }
     }
     return getSayHelloServerStreamingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest,
      com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloClientStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHelloClientStreaming",
      requestType = com.example.grpc.protodefine.Helloworld.HelloRequest.class,
      responseType = com.example.grpc.protodefine.Helloworld.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest,
      com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloClientStreamingMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest, com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloClientStreamingMethod;
    if ((getSayHelloClientStreamingMethod = HelloWorldGrpc.getSayHelloClientStreamingMethod) == null) {
      synchronized (HelloWorldGrpc.class) {
        if ((getSayHelloClientStreamingMethod = HelloWorldGrpc.getSayHelloClientStreamingMethod) == null) {
          HelloWorldGrpc.getSayHelloClientStreamingMethod = getSayHelloClientStreamingMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.protodefine.Helloworld.HelloRequest, com.example.grpc.protodefine.Helloworld.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.protodefine.HelloWorld", "SayHelloClientStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.Helloworld.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.Helloworld.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloWorldMethodDescriptorSupplier("SayHelloClientStreaming"))
                  .build();
          }
        }
     }
     return getSayHelloClientStreamingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest,
      com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloStreamingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayHelloStreaming",
      requestType = com.example.grpc.protodefine.Helloworld.HelloRequest.class,
      responseType = com.example.grpc.protodefine.Helloworld.HelloReply.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest,
      com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloStreamingMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.HelloRequest, com.example.grpc.protodefine.Helloworld.HelloReply> getSayHelloStreamingMethod;
    if ((getSayHelloStreamingMethod = HelloWorldGrpc.getSayHelloStreamingMethod) == null) {
      synchronized (HelloWorldGrpc.class) {
        if ((getSayHelloStreamingMethod = HelloWorldGrpc.getSayHelloStreamingMethod) == null) {
          HelloWorldGrpc.getSayHelloStreamingMethod = getSayHelloStreamingMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.protodefine.Helloworld.HelloRequest, com.example.grpc.protodefine.Helloworld.HelloReply>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.protodefine.HelloWorld", "SayHelloStreaming"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.Helloworld.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.Helloworld.HelloReply.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloWorldMethodDescriptorSupplier("SayHelloStreaming"))
                  .build();
          }
        }
     }
     return getSayHelloStreamingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.PerforData,
      com.example.grpc.protodefine.Helloworld.PerforData> getSayPerformacneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SayPerformacne",
      requestType = com.example.grpc.protodefine.Helloworld.PerforData.class,
      responseType = com.example.grpc.protodefine.Helloworld.PerforData.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.PerforData,
      com.example.grpc.protodefine.Helloworld.PerforData> getSayPerformacneMethod() {
    io.grpc.MethodDescriptor<com.example.grpc.protodefine.Helloworld.PerforData, com.example.grpc.protodefine.Helloworld.PerforData> getSayPerformacneMethod;
    if ((getSayPerformacneMethod = HelloWorldGrpc.getSayPerformacneMethod) == null) {
      synchronized (HelloWorldGrpc.class) {
        if ((getSayPerformacneMethod = HelloWorldGrpc.getSayPerformacneMethod) == null) {
          HelloWorldGrpc.getSayPerformacneMethod = getSayPerformacneMethod = 
              io.grpc.MethodDescriptor.<com.example.grpc.protodefine.Helloworld.PerforData, com.example.grpc.protodefine.Helloworld.PerforData>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "com.example.grpc.protodefine.HelloWorld", "SayPerformacne"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.Helloworld.PerforData.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.grpc.protodefine.Helloworld.PerforData.getDefaultInstance()))
                  .setSchemaDescriptor(new HelloWorldMethodDescriptorSupplier("SayPerformacne"))
                  .build();
          }
        }
     }
     return getSayPerformacneMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloWorldStub newStub(io.grpc.Channel channel) {
    return new HelloWorldStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloWorldBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new HelloWorldBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloWorldFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new HelloWorldFutureStub(channel);
  }

  /**
   */
  public static abstract class HelloWorldImplBase implements io.grpc.BindableService {

    /**
     */
    public void sayHello(com.example.grpc.protodefine.Helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloMethod(), responseObserver);
    }

    /**
     */
    public void sayHelloServerStreaming(com.example.grpc.protodefine.Helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply> responseObserver) {
      asyncUnimplementedUnaryCall(getSayHelloServerStreamingMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloRequest> sayHelloClientStreaming(
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getSayHelloClientStreamingMethod(), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloRequest> sayHelloStreaming(
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply> responseObserver) {
      return asyncUnimplementedStreamingCall(getSayHelloStreamingMethod(), responseObserver);
    }

    /**
     */
    public void sayPerformacne(com.example.grpc.protodefine.Helloworld.PerforData request,
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.PerforData> responseObserver) {
      asyncUnimplementedUnaryCall(getSayPerformacneMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSayHelloMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.protodefine.Helloworld.HelloRequest,
                com.example.grpc.protodefine.Helloworld.HelloReply>(
                  this, METHODID_SAY_HELLO)))
          .addMethod(
            getSayHelloServerStreamingMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.example.grpc.protodefine.Helloworld.HelloRequest,
                com.example.grpc.protodefine.Helloworld.HelloReply>(
                  this, METHODID_SAY_HELLO_SERVER_STREAMING)))
          .addMethod(
            getSayHelloClientStreamingMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.example.grpc.protodefine.Helloworld.HelloRequest,
                com.example.grpc.protodefine.Helloworld.HelloReply>(
                  this, METHODID_SAY_HELLO_CLIENT_STREAMING)))
          .addMethod(
            getSayHelloStreamingMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.example.grpc.protodefine.Helloworld.HelloRequest,
                com.example.grpc.protodefine.Helloworld.HelloReply>(
                  this, METHODID_SAY_HELLO_STREAMING)))
          .addMethod(
            getSayPerformacneMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.example.grpc.protodefine.Helloworld.PerforData,
                com.example.grpc.protodefine.Helloworld.PerforData>(
                  this, METHODID_SAY_PERFORMACNE)))
          .build();
    }
  }

  /**
   */
  public static final class HelloWorldStub extends io.grpc.stub.AbstractStub<HelloWorldStub> {
    private HelloWorldStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloWorldStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloWorldStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloWorldStub(channel, callOptions);
    }

    /**
     */
    public void sayHello(com.example.grpc.protodefine.Helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void sayHelloServerStreaming(com.example.grpc.protodefine.Helloworld.HelloRequest request,
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getSayHelloServerStreamingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloRequest> sayHelloClientStreaming(
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getSayHelloClientStreamingMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloRequest> sayHelloStreaming(
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getSayHelloStreamingMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void sayPerformacne(com.example.grpc.protodefine.Helloworld.PerforData request,
        io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.PerforData> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSayPerformacneMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class HelloWorldBlockingStub extends io.grpc.stub.AbstractStub<HelloWorldBlockingStub> {
    private HelloWorldBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloWorldBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloWorldBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloWorldBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.grpc.protodefine.Helloworld.HelloReply sayHello(com.example.grpc.protodefine.Helloworld.HelloRequest request) {
      return blockingUnaryCall(
          getChannel(), getSayHelloMethod(), getCallOptions(), request);
    }

    /**
     */
    public java.util.Iterator<com.example.grpc.protodefine.Helloworld.HelloReply> sayHelloServerStreaming(
        com.example.grpc.protodefine.Helloworld.HelloRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getSayHelloServerStreamingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.grpc.protodefine.Helloworld.PerforData sayPerformacne(com.example.grpc.protodefine.Helloworld.PerforData request) {
      return blockingUnaryCall(
          getChannel(), getSayPerformacneMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class HelloWorldFutureStub extends io.grpc.stub.AbstractStub<HelloWorldFutureStub> {
    private HelloWorldFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private HelloWorldFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloWorldFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new HelloWorldFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.protodefine.Helloworld.HelloReply> sayHello(
        com.example.grpc.protodefine.Helloworld.HelloRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSayHelloMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.grpc.protodefine.Helloworld.PerforData> sayPerformacne(
        com.example.grpc.protodefine.Helloworld.PerforData request) {
      return futureUnaryCall(
          getChannel().newCall(getSayPerformacneMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SAY_HELLO = 0;
  private static final int METHODID_SAY_HELLO_SERVER_STREAMING = 1;
  private static final int METHODID_SAY_PERFORMACNE = 2;
  private static final int METHODID_SAY_HELLO_CLIENT_STREAMING = 3;
  private static final int METHODID_SAY_HELLO_STREAMING = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final HelloWorldImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(HelloWorldImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SAY_HELLO:
          serviceImpl.sayHello((com.example.grpc.protodefine.Helloworld.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply>) responseObserver);
          break;
        case METHODID_SAY_HELLO_SERVER_STREAMING:
          serviceImpl.sayHelloServerStreaming((com.example.grpc.protodefine.Helloworld.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply>) responseObserver);
          break;
        case METHODID_SAY_PERFORMACNE:
          serviceImpl.sayPerformacne((com.example.grpc.protodefine.Helloworld.PerforData) request,
              (io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.PerforData>) responseObserver);
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
        case METHODID_SAY_HELLO_CLIENT_STREAMING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayHelloClientStreaming(
              (io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply>) responseObserver);
        case METHODID_SAY_HELLO_STREAMING:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.sayHelloStreaming(
              (io.grpc.stub.StreamObserver<com.example.grpc.protodefine.Helloworld.HelloReply>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class HelloWorldBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloWorldBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.grpc.protodefine.Helloworld.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HelloWorld");
    }
  }

  private static final class HelloWorldFileDescriptorSupplier
      extends HelloWorldBaseDescriptorSupplier {
    HelloWorldFileDescriptorSupplier() {}
  }

  private static final class HelloWorldMethodDescriptorSupplier
      extends HelloWorldBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    HelloWorldMethodDescriptorSupplier(String methodName) {
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
      synchronized (HelloWorldGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloWorldFileDescriptorSupplier())
              .addMethod(getSayHelloMethod())
              .addMethod(getSayHelloServerStreamingMethod())
              .addMethod(getSayHelloClientStreamingMethod())
              .addMethod(getSayHelloStreamingMethod())
              .addMethod(getSayPerformacneMethod())
              .build();
        }
      }
    }
    return result;
  }
}
