import API from './API.js'

export default {
    getAuthors() {
        return API().get('/get/authors')
    },

    postAuthor(data) {
        console.log(data)
        return API().post('/add/author', data)
    },
    
    deleteAuthor(id) {
        return API().delete('/delete/author/' + id)
    },

    getAuthorById(id) {
        return API().get('/get/author/' + id)
    },

    updateAuthor(id, data) {
        return API().put('/update/author/' + id, data)
    },

    getBooks() {
        return API().get('/get/books')
    },

    postBook(data) {
        console.log(data)
        return API().post('/add/book', data)
    },
    
    deleteBook(id) {
        return API().delete('/delete/book/' + id)
    },

    getBookById(id) {
        return API().get('/get/book/' + id)
    },

    updateBook(id, data) {
        return API().put('/update/book/' + id, data)
    }

}