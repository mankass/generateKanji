<template>
  <q-list class="modal">
    <q-card class="row" v-for="user in props.data">
      <q-card class="q-ma-lg-lg" bordered>{{ user }}</q-card>
      <q-btn @click="deleteUserFromDeck()">Delete</q-btn>
      <q-btn @click="addUserToDeck()">Add User</q-btn>
    </q-card>
  </q-list>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {Configuration, DeckAPIApi} from "../../../generated";

const deckApi = new DeckAPIApi(
  new Configuration({
    headers: {
      Authorization: "Bearer " + localStorage.getItem("jwt"),
    },
  })
);

const props = defineProps({
  data: {
    type: Object,
    required: true,
  },
  deckId: {
    type: Object,
    required: true,
  },
});

async function addUserToDeck() {
  await deckApi.addUserToDeck({
    deckId: props.deckId,
    login: "test",
  });
}

async function deleteUserFromDeck() {
  await deckApi.deleteUserFromDeck({
    deckId: props.deckId,
    login: "test",
  });
}
</script>

<style scoped lang="sass">

.modal
  width: 500px
  height: 700px
  min-width: 200px
</style>
