package temp;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.42.1)",
    comments = "Source: temp.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TemperatureServiceGrpc {

  private TemperatureServiceGrpc() {}

  public static final String SERVICE_NAME = "temp.TemperatureService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<temp.SubscribeRequest,
      temp.TemperatureUpdate> getSubscribeTemperatureMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubscribeTemperature",
      requestType = temp.SubscribeRequest.class,
      responseType = temp.TemperatureUpdate.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<temp.SubscribeRequest,
      temp.TemperatureUpdate> getSubscribeTemperatureMethod() {
    io.grpc.MethodDescriptor<temp.SubscribeRequest, temp.TemperatureUpdate> getSubscribeTemperatureMethod;
    if ((getSubscribeTemperatureMethod = TemperatureServiceGrpc.getSubscribeTemperatureMethod) == null) {
      synchronized (TemperatureServiceGrpc.class) {
        if ((getSubscribeTemperatureMethod = TemperatureServiceGrpc.getSubscribeTemperatureMethod) == null) {
          TemperatureServiceGrpc.getSubscribeTemperatureMethod = getSubscribeTemperatureMethod =
              io.grpc.MethodDescriptor.<temp.SubscribeRequest, temp.TemperatureUpdate>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SubscribeTemperature"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  temp.SubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  temp.TemperatureUpdate.getDefaultInstance()))
              .setSchemaDescriptor(new TemperatureServiceMethodDescriptorSupplier("SubscribeTemperature"))
              .build();
        }
      }
    }
    return getSubscribeTemperatureMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TemperatureServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TemperatureServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TemperatureServiceStub>() {
        @java.lang.Override
        public TemperatureServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TemperatureServiceStub(channel, callOptions);
        }
      };
    return TemperatureServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TemperatureServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TemperatureServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TemperatureServiceBlockingStub>() {
        @java.lang.Override
        public TemperatureServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TemperatureServiceBlockingStub(channel, callOptions);
        }
      };
    return TemperatureServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TemperatureServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TemperatureServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TemperatureServiceFutureStub>() {
        @java.lang.Override
        public TemperatureServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TemperatureServiceFutureStub(channel, callOptions);
        }
      };
    return TemperatureServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TemperatureServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Client subscribes to temperature updates
     * </pre>
     */
    public void subscribeTemperature(temp.SubscribeRequest request,
        io.grpc.stub.StreamObserver<temp.TemperatureUpdate> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSubscribeTemperatureMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSubscribeTemperatureMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                temp.SubscribeRequest,
                temp.TemperatureUpdate>(
                  this, METHODID_SUBSCRIBE_TEMPERATURE)))
          .build();
    }
  }

  /**
   */
  public static final class TemperatureServiceStub extends io.grpc.stub.AbstractAsyncStub<TemperatureServiceStub> {
    private TemperatureServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TemperatureServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TemperatureServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Client subscribes to temperature updates
     * </pre>
     */
    public void subscribeTemperature(temp.SubscribeRequest request,
        io.grpc.stub.StreamObserver<temp.TemperatureUpdate> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSubscribeTemperatureMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TemperatureServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<TemperatureServiceBlockingStub> {
    private TemperatureServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TemperatureServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TemperatureServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Client subscribes to temperature updates
     * </pre>
     */
    public java.util.Iterator<temp.TemperatureUpdate> subscribeTemperature(
        temp.SubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSubscribeTemperatureMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TemperatureServiceFutureStub extends io.grpc.stub.AbstractFutureStub<TemperatureServiceFutureStub> {
    private TemperatureServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TemperatureServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TemperatureServiceFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_SUBSCRIBE_TEMPERATURE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TemperatureServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TemperatureServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SUBSCRIBE_TEMPERATURE:
          serviceImpl.subscribeTemperature((temp.SubscribeRequest) request,
              (io.grpc.stub.StreamObserver<temp.TemperatureUpdate>) responseObserver);
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

  private static abstract class TemperatureServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TemperatureServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return temp.TempProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TemperatureService");
    }
  }

  private static final class TemperatureServiceFileDescriptorSupplier
      extends TemperatureServiceBaseDescriptorSupplier {
    TemperatureServiceFileDescriptorSupplier() {}
  }

  private static final class TemperatureServiceMethodDescriptorSupplier
      extends TemperatureServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TemperatureServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TemperatureServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TemperatureServiceFileDescriptorSupplier())
              .addMethod(getSubscribeTemperatureMethod())
              .build();
        }
      }
    }
    return result;
  }
}
