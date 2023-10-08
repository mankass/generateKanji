<template>
  <q-page-container>
    <q-card class="q-ma-md container-buttons">
      <q-btn class="q-ma-xs" color="green" @click="dialogNew = true">
        add new word</q-btn
      >
      <q-btn class="q-ma-xs" color="orange" @click="dialogNew = true">
        Edit word</q-btn
      >
    </q-card>

    <div class="q-ma-md">
      <div class="q-a-lg-lgd">
        <q-table
          :rows-per-page-options="[15, 25]"
          flat
          bordered
          title="Words"
          :rows="wordsList"
          :columns="columnsTest"
          row-key="name"
          dark
          color="amber"
        ></q-table>
      </div>
    </div>

    <q-dialog v-model="dialogNew">
      <NewWords></NewWords>
    </q-dialog>
    <router-view />
  </q-page-container>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { APIApi, WordData } from "../../../generated";
import NewWords from "../components/modals/NewWords.vue";

const api = new APIApi();
const wordsList = ref<WordData[]>();
const dialogNew = ref(false);
const columnsTest = [
  {
    name: "word",
    label: "Word",
    align: "left",
    field: "word",
    sortable: true,
  },
  {
    name: "translate",
    align: "center",
    label: "Translate",
    field: "translate",
    sortable: true,
  },
  {
    name: "transcription",
    align: "center",
    label: "Transcription",
    field: "transcription",
    sortable: true,
  },
];

async function getAllWords() {
  console.log("starrt");
  wordsList.value = await api.getAllWords();
}

getAllWords();
</script>

<style lang="sass" scoped>

.container-buttons
  background: #f2c037
  color: black

.button-text
  background: #21ba45
  color: bisque

.controller
  display: flex
  justify-content: space-between
  height: 200px

.container
  display: flex
  flex-direction: column
  justify-content: space-between

.question
  display: inline-block
  background: #5799f5
  font-size: 40px
  margin-right: 10px

.question-container
  margin-left: 40px
  margin-top: 40px
</style>
