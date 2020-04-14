import React, { useState, useCallback } from 'react';
import {
  StyleSheet,
  View,
  Button,
  Text,
  Image,
  ScrollView,
  Picker,
  Switch,
  Platform,
} from 'react-native';
import {
  GiphyUi,
  GiphyGif,
  GiphyMediaType,
  GiphyTheme,
  GiphyUIRating,
  GiphyMediaView,
} from 'react-native-giphy-ui';

GiphyUi.configure('g1zE0iMVRsYdw03HXZfRd3ivUjxEywFB');

export default function App() {
  const [gif, setGif] = useState<GiphyGif | undefined>();
  const [theme, setTheme] = useState<GiphyTheme>();
  const [rating, setRating] = useState<GiphyUIRating>('ratedPG');
  const [mediaTypes, setMediaTypes] = useState<GiphyMediaType[]>(['gifs']);
  const [shouldLocalizeSearch, setShouldLocalizeSearch] = useState(false);
  const [showConfirmationScreen, setShowConfirmationScreen] = useState(false);
  const [useNativeMediaView] = useState(false);

  const openGifPicker = useCallback(() => {
    setGif(undefined);
    GiphyUi.present(
      {
        mediaTypes,
        theme,
        rating,
        showConfirmationScreen,
        shouldLocalizeSearch,
      },
      setGif
    );
  }, [mediaTypes, theme, rating, showConfirmationScreen, shouldLocalizeSearch]);

  console.log(gif);

  return (
    <>
      <ScrollView
        style={styles.container}
        contentContainerStyle={styles.content}
        contentInset={{ bottom: 500 }}
      >
        {gif && (
          <View style={styles.gif}>
            <Text style={styles.gifTitle}>
              Selected Gif {useNativeMediaView && '(Native View)'}
            </Text>
            {useNativeMediaView ? (
              <GiphyMediaView media={gif} />
            ) : (
              <Image
                source={{ uri: gif.images.downsized.url }}
                style={styles.gifImage}
              />
            )}
          </View>
        )}
        <View style={styles.settings}>
          <View style={styles.setting}>
            <Text style={styles.settingTitle}>Theme</Text>
            <Picker
              selectedValue={theme}
              onValueChange={value => setTheme(value)}
              style={styles.picker}
            >
              <Picker.Item label="Light" value="light" />
              <Picker.Item label="Dark" value="dark" />
            </Picker>
          </View>
          <View style={styles.setting}>
            <Text style={styles.settingTitle}>Media Types</Text>
            <Picker
              selectedValue={mediaTypes[0]}
              onValueChange={value => setMediaTypes([value])}
              style={styles.picker}
            >
              <Picker.Item label="Gifs" value="gifs" />
              <Picker.Item label="Emojis" value="emoji" />
              <Picker.Item label="Stickers" value="stickers" />
              <Picker.Item label="Text" value="text" />
            </Picker>
          </View>
          <View style={styles.setting}>
            <Text style={styles.settingTitle}>Rating</Text>
            <Picker
              selectedValue={rating}
              onValueChange={value => setRating(value)}
              style={styles.picker}
            >
              <Picker.Item label="PG" value="ratedPG" />
              <Picker.Item label="PG 13" value="ratedPG13" />
              <Picker.Item label="Unrated" value="unrated" />
              <Picker.Item label="Rated R" value="ratedR" />
              <Picker.Item label="Rated Y" value="ratedY" />
              <Picker.Item label="Rated G" value="ratedG" />
              <Picker.Item label="NSFW" value="nsfw" />
            </Picker>
          </View>
          {Platform.OS === 'ios' && (
            <>
              <View style={styles.settingRow}>
                <Text style={styles.settingTitle}>
                  Show Confirmation Screen
                </Text>
                <Switch
                  value={showConfirmationScreen}
                  onValueChange={setShowConfirmationScreen}
                />
              </View>
              <View style={styles.settingRow}>
                <Text style={styles.settingTitle}>Localize Search</Text>
                <Switch
                  value={shouldLocalizeSearch}
                  onValueChange={setShouldLocalizeSearch}
                />
              </View>
              {/* <View style={styles.settingRow}>
                <Text style={styles.settingTitle}>Use Native Media View</Text>
                <Switch
                  value={useNativeMediaView}
                  onValueChange={setUseNativeMediaView}
                />
              </View> */}
            </>
          )}
        </View>
      </ScrollView>
      <View style={styles.float}>
        <Button onPress={openGifPicker} title="Open Gif Picker" />
      </View>
    </>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    paddingTop: 25,
  },
  content: {
    paddingBottom: 100,
  },
  gif: {
    flexDirection: 'column',
    marginTop: 50,
  },
  gifTitle: {
    fontWeight: '500',
    marginBottom: 5,
    textAlign: 'left',
    paddingLeft: 15,
  },
  gifImage: {
    width: 200,
    height: 200,
    marginLeft: 15,
    marginTop: 10,
  },
  settings: {
    flex: 1,
    maxHeight: 500,
    width: '100%',
    marginBottom: 25,
    marginTop: 25,
  },
  settingTitle: {
    fontWeight: '500',
    marginBottom: 5,
    textAlign: 'left',
    paddingLeft: 15,
  },
  setting: {
    borderBottomWidth: 0.5,
    borderBottomColor: 'lightgray',
    marginTop: 15,
  },
  picker: {
    width: '100%',
    borderTopWidth: 0.5,
    borderTopColor: 'lightgray',
    marginTop: 10,
  },
  float: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    marginBottom: 25,
    backgroundColor: 'black',
  },
  settingRow: {
    borderBottomWidth: 0.5,
    borderBottomColor: 'lightgray',
    marginBottom: 15,
    flexDirection: 'row',
    justifyContent: 'space-between',
    paddingVertical: 15,
  },
});
