<template>
  <q-card>
    <q-card-section class="row">
      <div class="text-h6">Войти</div>
      <q-space></q-space>
      <q-btn v-close-popup outline round icon="close" dense/>
    </q-card-section>

    <q-card-section class="q-pt-none">
      <q-input outlined label="Login" v-model="login"/>
      <q-input outlined label="Password" v-model="password"/>
    </q-card-section>

    <q-card-actions align="right">
      <q-btn flat @click="auth()" label="Войти" color="primary" v-close-popup/>
    </q-card-actions>
  </q-card>
</template>

<script lang="ts" setup>
import {ref} from "vue";
import {
  Configuration,
  SECURITYAPIApi,
} from "../../../../generated/client-sdk";
import {APIApi} from "../../../../generated";

const login = ref<string>("");
const password = ref<string>("");
const translate = ref<string>("");
let api = new APIApi();
const secApi = new SECURITYAPIApi();
const jwt = ref<string>("");

async function auth() {
  jwt.value = await secApi.createAuthToken({
    login: login.value,
    password: password.value,
  });
  localStorage.setItem("jwt", jwt.value);
  api = new APIApi(
    new Configuration({
      headers: {
        Authorization: localStorage.getItem("token"),
      },
    })
  );
}
</script>

<style scoped></style>
