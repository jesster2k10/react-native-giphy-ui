import { NativeModules } from 'react-native';
import { GiphyGif } from './types';

const { RNGiphyUI } = NativeModules;

export type GiphyTheme = 'light' | 'dark';
export type GiphyLayout = 'carousel' | 'waterfall';
export type GiphyMediaType = 'gifs' | 'stickers' | 'text' | 'emoji';
export type Callback = (() => void) | null;
export type GiphyUIRating =
  | 'nsfw'
  | 'ratedPG13'
  | 'ratedPG'
  | 'unrated'
  | 'ratedR'
  | 'ratedY'
  | 'ratedG';

export interface GiphyPresentConfig {
  theme?: GiphyTheme;
  layout?: GiphyLayout;
  rating?: GiphyUIRating;
  trayHeightMultiplier?: number;
  showConfirmationScreen?: boolean;
  shouldLocalizeSearch?: boolean;
  mediaTypes?: GiphyMediaType[];
}

class GiphyUI {
  static present(
    config: GiphyPresentConfig,
    onSelect: (media: GiphyGif) => void,
    onDismiss: Callback = () => {}
  ) {
    RNGiphyUI.present(config, onSelect, onDismiss);
  }

  static configure(apiKey: string) {
    RNGiphyUI.configure(apiKey);
  }
}

export default GiphyUI;
