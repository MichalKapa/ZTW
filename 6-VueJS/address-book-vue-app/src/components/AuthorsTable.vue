<template>
    <div id="authors-table">
    <table>
    <!-- ...thead... -->
    <tbody>
    <tr v-for="author in authorsSource" :key="author.id">
    <td>{{ author.id }}</td>
    <td>{{ author.firstName }}</td>
    <td>{{ author.lastName }}</td>
 </tr>
 </tbody>
 </table>
 </div>
</template>
   <script>
    export default { 
        name: 'authors-table',
            props: {
                authorsSource: Array,
            },
    }
    </script>
<style scoped></style>