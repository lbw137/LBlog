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
    default: '1.5'
  }
});
const Icon = '#' + props.icon;
const Scale = `width: calc(1rem*${props.scale}); height: calc(1rem*${props.scale})`;
</script>

<style scoped lang="scss"></style>
