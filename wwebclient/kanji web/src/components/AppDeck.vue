<template>
  <q-card class="q-card-deck">
    <q-card class="q-ml-lg q-card-deck-2">
      <div>{{ props.data.listUsers }}</div>
      Name: {{ props.data.name }}
    </q-card>

    <q-card-actions class="deck-action">
      <q-btn
        color="grey"
        round
        flat
        dense
        :icon="expanded ? 'keyboard_arrow_up' : 'keyboard_arrow_down'"
        @click="expanded = !expanded"
      ></q-btn>
      <q-btn @click="addToDeck(props.data.id)">Add word to Deck</q-btn>
      <q-btn @click="deleteDeck(props.data.id)">Remove deck</q-btn>
      <q-btn @click="showPrivacy = true">Privacy</q-btn>
    </q-card-actions>

    <q-slide-transition class="slide-deck">
      <div v-show="expanded">
        <q-separator></q-separator>
        <div>
          <q-card-section class="text-subtitle2">
            <q-card class="row deck-action" v-for="word in props.data.listWords">
              <div class="q-ma-xs">
                {{ word.wordData.word }} - {{ word.wordData.translate }} [{{
                  word.wordData.transcription
                }}]
              </div>
              <div>Stat:{{ word.percentCorrect }}</div>
              <q-btn class="q-ml-lg" color="red">Delete from deck</q-btn>
            </q-card>
          </q-card-section>
        </div>
      </div>
    </q-slide-transition>
  </q-card>
  <q-dialog v-model="showPrivacy">
    <privasu-list
      :deck-id="props.data.id"
      :data="props.data.listUsers"
    ></privasu-list>
  </q-dialog>
</template>

<script lang="ts" setup>
import {Configuration, DeckAPIApi} from "../../../generated";
import {ref} from "vue";
import PrivasuList from "components/PrivasuList.vue";

const showPrivacy = ref<boolean>(false);

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

async function deleteDeck(deckId: string) {
  await deckApi.delete1({
    id: deckId,
  });
}
</script>

<style scoped lang="sass">

.slide-deck
  color: #000000
  background: #f2c037

.q-card-deck
  background: rgba(25, 145, 210, 0.47)

.q-card-deck-2
  background: #1976d2

.deck-action
  background: #21ba45

.appDeck
  width: 80%
</style>
