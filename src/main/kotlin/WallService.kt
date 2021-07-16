class PostNotFoundException(message: String) : RuntimeException(message)
class WallService {
    private var posts = arrayOf<Post>()
    private var comments = emptyArray<Comment>()

    fun createComment(comment: Comment){
        for(currentPost in posts){
            if(currentPost.id == comment.postId){
                comments += comment
                return
            }

        throw PostNotFoundException("Post to comment does not exist")
        }
    }
    fun getPostComments(post: Post): String {
        var commentsString = ""
        for (comment in comments) {
            if (comment.replyToComment == post.id) {

                commentsString += comment.message

            }
        }
        return commentsString
    }


    fun add(post: Post): Post {
        posts += if (posts.isEmpty()) post.copy(id = 1)
        else post.copy(id = posts.last().id + 1)
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

    fun findPostById(id: Int): Post? {
        for ((index, currentPost) in posts.withIndex()) {
            if (currentPost.id == id) {
                return posts[index]
            }
        }
        return null
    }
}