class WallService {
    private var posts = arrayOf<Post>()

    fun add(post: Post): Post {
        posts += post.copy(posts.lastOrNull()?.id ?: 0)
        return posts.last()

    }

    fun update(post: Post): Boolean {
        for ((index, currentPost) in posts.withIndex()) {
            if (currentPost.id == post.id) {
                posts[index] = post.copy(ownerId = currentPost.ownerId, date = currentPost.date)
                return true
            }

        }
        return false
    }

    fun getLastPostId(): Int = if (posts.isEmpty()) 0 else posts.last().id

}