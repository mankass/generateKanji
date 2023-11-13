<template>
  <q-card class="app-card">
    <q-input v-model="deckNewName">
      <div>rofl</div>
    </q-input>
    <q-btn @click="$emit('choose-deckName', deckNewName)"> Add to deck</q-btn>
  </q-card>
</template>

<script lang="ts" setup>
import {ref} from "vue";

const deckNewName = ref<string>("");
const emit = defineEmits<{
  (e: "choose-deckName"): number;
  (e: "update", value: string): void;
}>();
</script>

<style scoped lang="sass">

.app-card
  min-width: 50vw
  width: 50vw
  max-width: 50vw
</style>
