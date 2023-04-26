<template>
  <div class="go-back-container container">
    <router-link to="/">
      <button class="btn">Powrót</button>
    </router-link>
  </div>
  <div id="authors-table" class="container">
    <h1>Autorzy</h1>
    <table>
      <thead>
        <tr>
          <th>Imię</th>
          <th>Nazwisko</th>
          <th>Usuń</th>
          <th>Edytuj</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="author in authors" :key="author.id">
          <td>{{ author.firstName }}</td>
          <td>{{ author.lastName }}</td>
          <td>
            <button class="btn btn-red" @click="deleteAuthor(author.id)">
              Usuń
            </button>
          </td>
          <td>
            <button
              class="btn btn-yellow"
              @click="
                this.$router.push({
                  name: 'authorsUpdate',
                  params: { id: author.id },
                })
              "
            >
            Edytuj
            </button>
          </td>
        </tr>
      </tbody>
    </table>
    <AuthorForm :authorsSource="authors" class="mt-5" />
  </div>
</template>

<script>
import AuthorForm from "@/components/authors/AuthorsForm.vue";
import LibraryAPI from '../../services/LibraryAPI'
export default {
  name: "AuthorsTable",
  components: { AuthorForm },
  data() {
    return {
      authors: [],
    };
  },
  mounted() {
    this.loadAuthors()
  },
  methods: {
    async loadAuthors() {
      try {
        const response = await LibraryAPI.getAuthors()
        const data = await response.data
        this.authors = data
        } catch (err) {
            console.log(err)
        }
      },
    async deleteAuthor(itemId) {
      const response = await LibraryAPI.deleteAuthor(itemId)
      console.log(response.data);
      this.authors = this.authors.filter((obj) => {
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
