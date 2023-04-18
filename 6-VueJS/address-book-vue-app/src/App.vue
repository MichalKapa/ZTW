<template>
  <a href="authors">Autorzy</a> |
  <a href="books">Książki</a> |
  <component :is="currentView" />
</template>

<script>
import AuthorsPage from './components/AuthorsPage.vue'
import HomePage from './components/HomePage.vue'
import NotFound from './components/NotFound.vue'

const routes = {
  '/': HomePage,
  '/authors': AuthorsPage
}

export default {
  data() {
    return {
      currentPath: window.location.hash
    }
  },
  computed: {
    currentView() {
      return routes[this.currentPath.slice(1) || '/'] || NotFound
    }
  },
  mounted() {
    window.addEventListener('hashchange', () => {
    this.currentPath = window.location.hash
		})
  }
}
</script>