import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class WallServiceTest {

    @Test
    fun add() {
        val service = WallService()
        val expected = service.getLastPostId() + 1
        service.add(post = Post())
        assertEquals(expected, service.getLastPostId())
    }

    @Test
    fun updateExisting() {
        val service = WallService()
        service.add(Post(text = "Первое сообщение"))
        service.add(Post(text = "Второе сообщение"))
        service.add(Post())

        val updatePost = Post(2, text = "Второе сообщение обновлено")
        val result = service.update(updatePost)
        assertTrue(result)

    }

    @Test
    fun updateNotExisting() {
        val service = WallService()
        service.add(Post(text = "Первое сообщение"))
        service.add(Post(text = "Второе сообщение"))
        service.add(Post(text = "Третье сообщение"))

        val updatePost = Post(7, text = "Второе сообщение обновлено")
        val result = service.update(updatePost)
        assertTrue(!result)
    }
    @Test
    fun createComment(){
        val service = WallService()
        val post = Post(text = "Comment")
        service.add(post)

        val comment = Comment(
            replyToComment = service.getLastPostId(), message = "comment",
            ownerId = 1, postId = 1,
            attachments = arrayOf(CommentAttachment("att", 1, 1))
        )

        service.createComment(comment)
        val result = service.findPostById(service.getLastPostId())?.let { service.getPostComments(it) }


        assertEquals("comment", result)
    }
    @Test(expected = PostNotFoundException::class)
    fun shouldThrow(){
        val service = WallService()
        val post = Post(text = "Comment")
        service.add(post)

        val comment = Comment(
            replyToComment = service.getLastPostId(), message = "comment",
            ownerId = 1, postId = 2,
            attachments = arrayOf(CommentAttachment("att", 1, 1))
        )

        service.createComment(comment)

    }
    }


