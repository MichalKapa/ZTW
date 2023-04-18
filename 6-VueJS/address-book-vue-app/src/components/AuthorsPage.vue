<template>
    <div id="authors-page" class="small-container">
    <h1>Autorzy</h1>
    <author-add @add:author="addAuthor"/>
    <authors-table :authorsSource="authors" />
    </div>
   </template>
   <script>
    import AuthorsTable from '@/components/AuthorsTable.vue'
    import AuthorAdd from '@/components/AuthorAdd.vue'
    import LibraryAPI from '../services/LibraryAPI'
  
   export default {
    name: 'authors-page',
    components: {
      AuthorsTable,
      AuthorAdd,
    },
    data() {
      return {
        authors: [
          // {
          //   id: 1,
          //   name: 'Adam Słodowy',
          //   email: 'adam.slodowy@zrobtosam.pl',
          //   phone: '+48 787 774 664'
          // },
          // {
          //   id: 2,
          //   name: 'Michał Studencki',
          //   email: 'ms@student.pwr.edu.pl',
          //   phone: '+48 600 565 454'
          // },
          // { 
          //   id: 3,
          //   name: 'Kamila Napokaz',
          //   email: 'kami2003@h2.pl',
          //   phone: '+48 609 554 987'
          // },
        ]
      }
    }, 
  
    setup() {
        const loadAuthors = async () => {
            try {
                const response = await LibraryAPI.getAuthors()
                const data = await response.data
                console.log( data )
            } catch (err) {
                console.log(err)
            }
            
        }
        loadAuthors()
      },
  
    methods: {
      addAuthor(author) {
        this.authors = [...this.authors, author]
      },
    }, 
   } 
   </script>
   <style>
    button {
    background: #009435;
    border: 1px solid #009435;
    }
    .small-container {
    max-width: 680px;
    }
    </style>