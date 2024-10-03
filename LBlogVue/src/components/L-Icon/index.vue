<template>
  <svg class="icon" aria-hidden="true" :style="Scale">
    <use :xlink:href="Icon"></use>
  </svg>
</template>

<script setup lang="ts">
const props = defineProps({
  icon: {
    type: String,
    required: true
  },
  scale: {
    type: String,
    default: '1'
  }
});
const Icon = '#' + props.icon;
const Scale = 'transform: scale(' + props.scale + ');';
</script>

<style scoped lang="scss"></style>
