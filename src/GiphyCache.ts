import { NativeModules } from 'react-native';
const { RNGiphyCache } = NativeModules;

class GiphyCache {
  static setDiskCacheByteLimit(limit: number) {
    RNGiphyCache.setDiskCacheByteLimit(limit);
  }

  static setSetting(setting: 'memoryOnly' | 'diskOnly') {
    RNGiphyCache.setCacheSetting(setting);
  }

  static downloadAssetUrl(
    url: string
  ): Promise<{
    width: number;
    height: number;
    data: string;
  }> {
    return RNGiphyCache.downloadAssetUrl(url);
  }
}

export default GiphyCache;
