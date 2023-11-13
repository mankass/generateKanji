<template>
  <q-card>
    <q-card-section class="row">
      <div class="text-h6">Add new word</div>
      <q-space></q-space>
      <q-btn v-close-popup outline round icon="close" dense />
    </q-card-section>

    <q-card-section class="q-pt-none">
      <q-input outlined label="Word" v-model="word" />
      <q-input outlined label="transcription" v-model="transcription" />
      <q-input outlined label="translate" v-model="translate" />
    </q-card-section>

    <q-card-actions align="right">
      <q-btn
        flat
        @click="saveWord()"
        label="Сохранить"
        color="primary"
        v-close-popup
      />
    </q-card-actions>
  </q-card>
</template>

<script lang="ts" setup>
import { ref } from "vue";
import { safe } from "../../../../generated/client-sdk";
import { APIApi } from "../../../../generated";

const word = ref<string>(" ");
const transcription = ref<string>("");
const translate = ref<string>("");
const api = new APIApi();

async function saveWord() {
  await safe(
    async () => {
      try {
        await api.createWord({
          wordView: {
            word: word.value,
            transcription: transcription.value,
            translate: translate.value,
          },
        });
      } finally {
        console.log("final");
      }
    },
    { loading: false }
  );
}
</script>

<style scoped></style>
