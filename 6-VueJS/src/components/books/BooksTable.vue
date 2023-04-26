<template>
  <div class="go-back-container container">
    <router-link to="/">
      <button class="btn btn-info">Powrót</button>
    </router-link>
  </div>
  <div id="books-table" class="container">
    <h1>Lista książek</h1>
    <table>
      <thead>
        <tr>
          <th>Tytuł</th>
          <th>Imię autora</th>
          <th>Nazwisko autora</th>
          <th>Ilość stron</th>
          <th>Usuń</th>
          <th>Aktualizuj</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="book in books" :key="book.id">
          <td>{{ book.title }}</td>
          <td>{{ book.author.firstName }}</td>
          <td>{{ book.author.lastName }}</td>
          <td>{{ book.pages }}</td>
          <td>
            <button class="btn btn-red" @click="deleteBook(book.id)">
              Usuń
            </button>
          </td>
          <td>
            <button
              class="btn btn-yellow"
              @click="
                this.$router.push({
                  name: 'booksUpdate',
                  params: { id: book.id },
                })
              "
            >
              Aktualizuj
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <BookForm :booksSource="books" class="mt-5" />
  </div>
</template>

<script>
import LibraryAPI from '../../services/LibraryAPI'
import BookForm from "@/components/books/BooksForm.vue";
export default {
  name: "booksTable",
  components: { BookForm },
  data() {
    return {
      books: [],
    };
  },
  mounted() {
    this.loadBooks();
  },
  methods: {
    async loadBooks() {
      try {
        const response = await LibraryAPI.getBooks()
        const data = await response.data
        this.books = data
        } catch (err) {
            console.log(err)
        }
        console.log(this.books)

      },

      async deleteBook(itemId) {
      const response = await LibraryAPI.deleteBook(itemId)
      console.log(response.data);
      this.books = this.books.filter((obj) => {
        return obj.id !== itemId;
      });
    },
  },
};
</script>

<style scoped>
.go-back-container {
  padding-top: 20px;
  padding-bottom: 20px;
}

.btn-red {
  background-color: red;
}

.btn-yellow {
  background-color: #c6ca4d;
}
</style>
