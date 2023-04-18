<template>
  <div id="person-form" class="container">
    <h1>Edytuj dane autora</h1>
    <form @submit.prevent="handleSubmit">
      <label>Imię</label>
      <input
        v-model="author.firstName"
        type="text"
        :class="{ 'has-error': submitting && invalidFirstName }"
        @focus="clearStatus"
        @keypress="clearStatus"
        :placeholder="author.firstName"
      />
      <label>Nazwisko</label>
      <input
        v-model="author.lastName"
        type="text"
        :class="{ 'has-error': submitting && invalidLastName }"
        @focus="clearStatus"
        :placeholder="author.lastName"
      />
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">Dane poprawnie zapisano</p>
      <button class="btn btn-success">Zapisz</button>
    </form>
  </div>
</template>

  <script>
  import LibraryAPI from '../../services/LibraryAPI'
export default {
  name: "AuthorsUpdate",
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      author: {
        id: 0,
        firstName: "",
        lastName: "",
      },
    };
  },
  mounted() {
    this.getAuthor();
  },
  methods: {
    async updateData() {
      try {
        const data = {
          id: this.author.id,
          firstName: this.author.firstName,
          lastName: this.author.lastName,
        };
        const response = await LibraryAPI.updateAuthor(this.$route.params.id, data)
        console.log(response)
        this.$router.push({ name: "authors" });
      } catch (error) {
        console.error(error);
      }
    },
    async getAuthor() {

      const response = await LibraryAPI.getAuthorById(this.$route.params.id)

      const data = await response.data;
      // Obsługa odpowiedzi serwera
      console.log(data);
      this.author.id = data.id,
      this.author.firstName = data.firstName;
      this.author.lastName = data.lastName;
    },
    handleSubmit() {
      this.submitting = true;
      this.clearStatus();
      if (this.invalidFirstName || this.invalidLastName) {
        this.error = true;
        return;
      }
      this.updateData();
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
</style>