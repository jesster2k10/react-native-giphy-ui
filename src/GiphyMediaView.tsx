import React from 'react';
import { requireNativeComponent, Platform } from 'react-native';
import { GiphyGif } from './types';

const RNGiphyMediaView = Platform.select({
  ios: requireNativeComponent('GiphyMediaView'),
  android: null,
});

interface GiphyMediaViewProps {
  media: GiphyGif;
  style?: object;
}

const GiphyMediaView = ({ media, style }: GiphyMediaViewProps) => {
  if (Platform.OS === 'ios') {
    return <RNGiphyMediaView media={media} style={style} />;
  }

  return null;
};

export default GiphyMediaView;
