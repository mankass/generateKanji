<template>
  <q-card>
    <q-card class="q-ml-lg">
      Name: {{ props.data.name }}
    </q-card>

    <q-card-actions>
      <q-btn
        color="grey"
        round
        flat
        dense
        :icon="expanded ? 'keyboard_arrow_up' : 'keyboard_arrow_down'"
        @click="expanded = !expanded"
      ></q-btn>
      <q-btn @click="addToDeck(props.data.id)">Add word to Deck</q-btn>
      <q-btn>Remove deck</q-btn>
      <q-btn>Privacy</q-btn>
    </q-card-actions>

    <q-slide-transition>
      <div v-show="expanded">
        <q-separator></q-separator>
        <div>
          <q-card-section class="text-subtitle2">
            <q-card class="row" v-for="word in props.data.listWords">
              <q-card dark bordered class="q-ma-xs">
                {{ word.wordData.word }} - {{ word.wordData.translate }} [{{
                  word.wordData.transcription
                }}]
              </q-card>
              <q-card>Stat:{{ word.percentCorrect }}</q-card>
              <q-btn class="q-ml-lg" color="red">Delete from deck</q-btn>
            </q-card>
          </q-card-section>
        </div>
      </div>
    </q-slide-transition>
  </q-card>
</template>

<script lang="ts" setup>
import {Configuration, DeckAPIApi} from "../../../generated";
import {ref} from "vue";

const expanded = ref(false);

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
});
console.log("props:" + props.data);
const deckApi = new DeckAPIApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);

async function addToDeck(idDeck: string) {
  await deckApi.addWordToDeck({
    idDeck: idDeck,
    wordId: "4028b8818b0f12e5018b0f1673bb0003",
  });
}
</script>

<style scoped lang="sass">

.appDeck
  width: 80%

</style>
