<template>
    <div id="author-add">
        <form @submit.prevent="handleSubmit">
            <label>Imię</label>
            <input
                v-model="author.firstName"
                type="text"
                :class="{ 'has-error': submitting && invalidFirstName }"
                @focus="clearStatus"
                :placeholder="author.firstName"
                required
            />
            <label>Nazwisko</label>
            <input
                v-model="author.lastName"
                type="text"
                :class="{ 'has-error': submitting && invalidLastName }"
                @focus="clearStatus"
                :placeholder="author.lastName"
                @keypress="clearStatus"
                required
            />
            <p v-if="error && submitting" class="error-message">
                Proszę wypełnić wskazane pola formularza
            </p>
            <p v-if="success" class="success-message">
                Dane poprawnie zapisano
            </p>
            <button>Dodaj autora</button>
        </form>
    </div>
</template>
<script>

import { ref } from 'vue'
import LibraryAPI from '../services/LibraryAPI'

 export default {
    setup() {
        // axios.get("http://localhost:8081/authors")

        const authors = ref('')
        const loadAuthors = async () => {
            try {
                const response = await LibraryAPI.getAuthors()
                authors.value = response.data
            } catch (err) {
                console.log(err)
            }
        }
        loadAuthors()
    },
    name: 'author-add',
    data() {
        return {
            submitting: false,
            error: false,
            success: false, 
            author: {
                firstName: '',
                lastName: '',
            },
        }
    },
    methods: {
        handleSubmit() {
            this.submitting = true
            this.clearStatus()
            //check form fields
            if (this.invalidFirstName || this.invalidLastName) {
                this.error = true
                return
            }
            // this.$emit('add:author', this.author)
            //clear form fields
            this.author = {
                firstName: '',
                lastName: '',
            } 
            this.error = false
            this.success = true
            this.submitting = false
        },

        clearStatus() {
            this.success = false
            this.error = false
        },

    }, 
    computed: {
        invalidFirstName() {
            return this.author.firstName === ''
        },
        invalidLastName() {
            return this.author.lastName === ''
        }
    }
}
</script>
<style scoped>
 form {
 margin-bottom: 2rem;
 }

 [class*='-message'] {
 font-weight: 500;
 }
 .error-message {
 color: #d33c40;
 }
 .success-message {
 color: #32a95d;
 }
</style>