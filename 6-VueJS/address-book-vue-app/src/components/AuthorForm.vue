<template>
    <div id="author-form">
        <form @submit.prevent="handleSubmit">
            <label>ID</label>
            <input
                v-model="author.id"
                type="text"
                :class="{ 'has-error': submitting && invalidID }"
                @focus="clearStatus"
                @keypress="clearStatus"
            />
            <label>Imię</label>
            <input
                v-model="author.firstName"
                type="text"
                :class="{ 'has-error': submitting && invalidFirstName }"
                @focus="clearStatus"
            />
            <label>Nazwisko</label>
            <input
                v-model="author.lastName"
                type="text"
                :class="{ 'has-error': submitting && invalidLastName }"
                @focus="clearStatus"
                @keypress="clearStatus"
            />
            <p v-if="error && submitting" class="error-message">
            Proszę wypełnić wskazane pola formularza
            </p>
            <p v-if="success" class="success-message">
            Dane poprawnie zapisano
            </p>
            <button>Dodaj kontakt</button>
        </form>
    </div>
</template>
<script>
 export default {
    name: 'author-form',
    data() {
        return {
            submitting: false,
            error: false,
            success: false, 
            author: {
                id: '',
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
            if (this.invalidID || this.invalidFirstName || this.invalidLastName) {
                this.error = true
                return
            }
            this.$emit('add:author', this.author)
            //clear form fields
            this.author = {
                id: '',
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
        invalidID() {
            return this.author.id === ''
        },
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