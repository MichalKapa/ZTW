import { createApp } from 'vue'
import {createRouter, createWebHistory} from "vue-router";
import App from './App.vue'

import AuthorsForm from "@/components/authors/AuthorsForm.vue"
import AuthorsTable from "@/components/authors/AuthorsTable.vue"
import AuthorsUpdate from "@/components/authors/AuthorsUpdate.vue"

import BooksUpdate from "@/components/books/BooksUpdate.vue"
import BooksForm from "@/components/books/BooksForm.vue"
import BooksTable from "@/components/books/BooksTable.vue"

import Start from "@/components/Start.vue"

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            component: Start,
        },
        {
            path: '/author-form',
            component: AuthorsForm,
        },
        {
            path: '/authors',
            name: 'authors',
            component: AuthorsTable,
        },
        {
            path: '/author-update/:id',
            name: 'authorsUpdate',
            component: AuthorsUpdate,
        },
        {
            path: '/books-form',
            component: BooksForm,
        },
        {
            path: '/books',
            name: 'books',
            component: BooksTable,
        },
        {
            path: '/books-update/:id',
            name: 'booksUpdate',
            component: BooksUpdate,
        },
    ]
})

createApp(App).use(router).mount('#app')
