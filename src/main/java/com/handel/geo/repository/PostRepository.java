package com.handel.geo.repository;

import com.handel.geo.model.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository("postRepository")
public interface PostRepository extends CrudRepository<Post, Integer>
//        extends JpaRepository<Post, Integer>
{
    @Query(value = "select * from POST order by POST_ID desc", nativeQuery = true)
    List<Post> getAllPosts();

    @Transactional
    @Modifying
    @Query("delete from Post p where p.postId=:id")
    void deletePostByPost_Id(@Param("id") Integer id);


    @Query(value = "select * from POST p where p.POST_ID=:id", nativeQuery = true)
    Post getPostById(@Param("id") Integer id);

//    @Transactional
//    @Modifying
//    @Query(value = "update POST p set p.topic =:post.getTopic()", nativeQuery = true)
//    void updatePost(@Param("post") Post post)
//    {post.getTopic()};
}