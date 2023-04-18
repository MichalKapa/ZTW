import API from './API.js'

export default {
    getAuthors() {
        return API().get('/get/authors')
    }
}