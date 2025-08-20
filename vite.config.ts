import { UserConfigFn } from 'vite';
import { overrideVaadinConfig } from './vite.generated';

const customConfig: UserConfigFn = (env) => ({
 base: '/',
  build: {
    rollupOptions: {
      external: [
        './target/theme-handle.js',
        './target/theme-loader-utils.js',
        './target/vaadin-dev-server-settings.json',
      ],
    },
  },
});

export default overrideVaadinConfig(customConfig);
