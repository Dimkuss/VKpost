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

        val updatePost = Post(
            7, text = "Второе сообщение обновлено", createdBy = null, replyOwnerId = null,
            replyPostId = null, signerId = null,
        )
        val result = service.update(updatePost)
        assertTrue(!result)
    }
}