<template>
  <q-page-container class="q-page-container">
    <div class="row menuDeck">
      <q-btn @click="createDeck()" color="orange">Create Deck</q-btn>
    </div>
    <div class="decks-container">
      <q-list class="list-decks">
        <q-card v-for="deck in decksList">
          <app-deck :data="deck"></app-deck>
        </q-card>
      </q-list>
    </div>
  </q-page-container>
</template>

<script lang="ts" setup>
import {Configuration, DeckAPIApi, DeckView,} from "../../../generated";
import {ref} from "vue";
import AppDeck from "components/AppDeck.vue";

const deckApi = new DeckAPIApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);
const decksList = ref<DeckView[]>();

async function getAllDecks() {
  console.log("getAllDecks");
  decksList.value = await deckApi.getALlDecks();
}

async function createDeck() {
  await deckApi.createDeck({
    name: "df",
  });
}

getAllDecks();
</script>

<style scoped lang="sass">
.list-decks
  max-width: 80%
  align-content: center

.decks-container
  place-content: center
  display: flex
  width: 80%
  min-width: 80%

.menuDeck
  background: #31ccec
  display: flex
  place-content: center

</style>
