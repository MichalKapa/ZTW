<template>
  <div id="person-form" class="container mt-5">
    <h1>Dodaj nową książkę</h1>
    <form @submit.prevent="handleSubmit">
      <label>Tytuł</label>
      <input
        v-model="book.title"
        type="text"
        :class="{ 'has-error': submitting && invalidTitle }"
        @focus="clearStatus"
        @keypress="clearStatus"
        required
      />
      <label>Liczba stron</label>
      <input
        v-model="book.pages"
        type="text"
        :class="{ 'has-error': submitting && invalidPages }"
        @focus="clearStatus"
        required
      />
      <label>Autor</label>
      <select v-model="book.author"
      required>
        <option
          v-for="a in authors"
          :key="a.id"
          :value="a"
          @focus="clearStatus"
        >
          {{ a.firstName + " " + a.lastName }}
        </option>
      </select>
      <p v-if="error && submitting" class="error-message">
        Proszę wypełnić wskazane pola formularza
      </p>
      <p v-if="success" class="success-message">Dane poprawnie zapisano</p>
      <button class="btn btn-green mt-4">Dodaj książkę</button>
    </form>
  </div>
</template>

<script>
import LibraryAPI from '../../services/LibraryAPI'
export default {
  name: "booksForm",
  data() {
    return {
      submitting: false,
      error: false,
      success: false,
      book: {
        title: "",
        pages: "",
        author: {
          id: 0,
          firstName: "-",
          lastName: "-",
        },
      },
      authors: [],
    };
  },
  mounted() {
    this.loadAuthors();
  },
  methods: {
    async postData() {
      const response = await LibraryAPI.postBook(JSON.stringify({
        title: this.book.title,
        authorId: this.book.author.id,
        pages: this.book.pages,
      }))
      console.log(response)
      window.location.reload();
    },
    async loadAuthors() {
      try {
        const response = await LibraryAPI.getAuthors()
        const data = await response.data
        this.authors = data
        } catch (err) {
            console.log(err)
        }
      },

    handleSubmit() {
      this.submitting = true;
      this.clearStatus();

      if (this.invalidTitle || this.invalidPages) {
        this.error = true;
        return;
      }
      this.postData();
      this.book = {
        title: "",
        pages: "",
        author: {
          id: 0,
          firstName: "",
          lastName: "",
        },
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
    invalidTitle() {
      return this.book.title === "";
    },
    invalidPages() {
      return this.book.pages === "";
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
