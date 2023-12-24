package com.vsushko.grpc.blog.client;

import com.google.protobuf.Empty;
import com.proto.blog.Blog;
import com.proto.blog.BlogId;
import com.proto.blog.BlogServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.io.PrintStream;

/**
 * @author vsushko
 */
public class BlogClient {

    private static BlogId createBlog(BlogServiceGrpc.BlogServiceBlockingStub stub) {
        try {
            BlogId createResponse = stub.createBlog(
                    Blog.newBuilder()
                            .setAuthor("Vasiliy")
                            .setTitle("New Blog")
                            .setContent("Hello World this is a new blog!")
                            .build()
            );
            System.out.println("Blog created: " + createResponse.getId());
            System.out.println();
            return createResponse;
        } catch (StatusRuntimeException e) {
            System.out.println("Couldn't create the blog");
            e.printStackTrace();
            return null;
        }
    }

    private static void run(ManagedChannel channel) {
        BlogServiceGrpc.BlogServiceBlockingStub stub = BlogServiceGrpc.newBlockingStub(channel);

        BlogId blogId = createBlog(stub);

        if (blogId == null) {
            return;
        }
        readBlog(stub, blogId);
        updateBlog(stub, blogId);
        listBlogs(stub, System.out);
        deleteBlog(stub, blogId);
    }

    private static Blog readBlog(BlogServiceGrpc.BlogServiceBlockingStub stub, BlogId blogId) {
        System.out.println("Reading blog...");
        stub.readBlog(blogId);

        try {
            Blog readResponse = stub.readBlog(blogId);

            System.out.println("Blog read:" + readResponse);
            return readResponse;
        } catch (StatusRuntimeException e) {
            System.out.println("Couldn't read the blog");
            e.printStackTrace();
            return null;
        }
    }

    private static Blog updateBlog(BlogServiceGrpc.BlogServiceBlockingStub stub, BlogId blogId) {
        try {
            Blog newBlog = Blog.newBuilder()
                    .setId(blogId.getId())
                    .setAuthor("Changed Author")
                    .setTitle("New Blog (updated)!")
                    .setContent("Hello world this is my first blog! I've added some more content")
                    .build();

            System.out.println("Updating blog...");
            stub.updateBlog(newBlog);

            System.out.println("Blog updated");
            System.out.println(newBlog);

            return newBlog;
        } catch (StatusRuntimeException e) {
            System.out.println("Couldn't update the blog");
            e.printStackTrace();
            return null;
        }
    }

    private static BlogId deleteBlog(BlogServiceGrpc.BlogServiceBlockingStub stub, BlogId blogId) {
        try {
            System.out.println("Deleting blog");
            stub.deleteBlog(blogId);

            System.out.println("Blog deleted: " + blogId.getId());
            return blogId;
        } catch (StatusRuntimeException e) {
            System.out.println("Couldn't delete the blog");
            e.printStackTrace();
            return null;
        }
    }

    static void listBlogs(BlogServiceGrpc.BlogServiceBlockingStub stub, PrintStream ps) {
        ps.println("Listing blogs...");
        stub.listBlogs(Empty.getDefaultInstance()).forEachRemaining(ps::println);
    }

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        run(channel);

        System.out.println("Shutting down...");

        channel.shutdown();
    }
}
