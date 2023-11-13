<template>
  <q-card class="app-card row">
    <q-select :options="options" v-model="searchType"></q-select>
    <q-input v-model="findWord" hint="  Введите слово"></q-input>
    <q-select
      :use-input="true"
      :option-label="(x) => x.word"
      v-model="addWord"
      clearable
      label="result"
      :options="findOptions"
    >
    </q-select>
    <q-btn @click="search()"> search</q-btn>
    <!--    <q-btn @click="addWordToDeck(addWord.id)"> Add to deck</q-btn>-->
    <q-btn @click="$emit('choose-word', addWord.id)"> Add to deck</q-btn>
  </q-card>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {
  APIApi,
  Configuration,
  WordAndStatAPIApi,
  WordData,
} from "../../../generated";

const searchType = ref<string>();
const result = ref<WordData[]>([]);
const wordApi = new APIApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);

const emit = defineEmits<{
  (e: "choose-word"): number;
  (e: "update", value: string): void;
}>();

const findOptions = ref<WordData[]>([]);
const findWord = ref<string>("");
const addWord = ref<WordData>();
const options = ["Перевод", "Слово", "Транскрипция"];

async function search() {
  switch (searchType.value) {
    case "Перевод": {
      result.value = await wordApi.searchWordByTranslate({
        string: findWord.value,
      });
      findOptions.value = result.value;
      console.log(result.value);
      break;
    }
    case "Слово": {
      result.value = await wordApi.searchWordByName({string: findWord.value});
      findOptions.value = result.value;
      break;
    }
    case "Транскрипция": {
      console.log("TODO не реализовано");
      break;
    }
  }
}
</script>

<style scoped lang="sass">
.app-card
  min-width: 50vw
  width: 50vw
  max-width: 50vw
</style>
