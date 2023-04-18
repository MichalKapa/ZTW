<template>
  <div id="person-form" class="container">
    <h1>Dodaj autora</h1>
    <form @submit.prevent="handleSubmit">
      <label>Imię</label>
      <input
        v-model="author.firstName"
        type="text"
        :class="{ 'has-error': submitting && invalidFirstName }"
        @focus="clearStatus"
        @keypress="clearStatus"
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
        required
      />
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">Dane poprawnie zapisano</p>
      <button class="btn btn-green">Dodaj autora</button>
    </form>
  </div>
</template>

<script>
import LibraryAPI from '../../services/LibraryAPI'
export default {
  name: "AuthorsForm",
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      author: {
        firstName: "",
        lastName: "",
      },
    };
  },
  methods: {
    async postData() {
      const response = await LibraryAPI.postAuthor(JSON.stringify({
        firstName: this.author.firstName,
        lastName: this.author.lastName
      }))
      console.log(response)
      window.location.reload();
    },
    authorId() {
      return this.$route.params.id;
    },
    handleSubmit() {
      this.submitting = true;
      this.clearStatus();
      if (this.invalidFirstName || this.invalidLastName) {
        this.error = true;
        return;
      }
      this.postData();
      this.author = {
        firstName: "",
        lastName: "",
      };
      this.error = false;
      this.success = true;
      this.submitting = false;
    },
    clearStatus() {
      this.success = false;
      this.error = false;
    },
  },
  computed: {
    invalidFirstName() {
      return this.author.firstName === "";
    },
    invalidLastName() {
      return this.author.lastName === "";
    },
  },
};
</script>
<style scoped>
form {
  margin-bottom: 2rem;
}
[class*="-message"] {
  font-weight: 500;
}
.error-message {
  color: #d33c40;
}
.success-message {
  color: #32a95d;
}

.btn-green {
  background-color: #32a95d;
}

</style>
