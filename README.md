# Giphy For React Native

![bunlde size](https://img.shields.io/bundlephobia/min/react-native-giphy-ui)

![monthy downloads](https://img.shields.io/npm/dm/react-native-giphy-ui)

Cross Platform wrapper around the [Giphy SDK](https://developers.giphy.com) for both iOS and Android, giving you access to [Giphy](https://giphy.com) large GIF library. Works well with [react-native-gifted-chat](https://github.com/FaridSafi/react-native-gifted-chat) and Typescript.

## Prerequisites

- You need to get an API Key from [Giphy Developers](https://developers.giphy.com)
- iOS 10.0+
- Swift 5.0

## Example

You can view the source code for the example application [here](./example/src/App.tsx)

### iOS Application

![iOS Demo](./ios-gif.gif)

## Installation

Install the library using either yarn or npm like so:

```sh
$ npm install --save react-native-giphy-ui
```

```sh
$ yarn add react-native-giphy-ui
```

### iOS Installation

If you're using React Native versions > 60.0, it's relatively straightforward.

```sh
cd ios && pod install
```

For versions below 0.60.0, use rnpm links

- Run `react-native link @jesster2k10/react-native-range-slider`
- If linking fails, follow the
  [manual linking steps](https://facebook.github.io/react-native/docs/linking-libraries-ios.html#manual-linking)

> NB: `use_frameworks!` must be added to the top of your Podfile in order for the app to compile with this library installed. Doing so will disable/break support for Flipper. As of now, there is not alternative.

#### Additional Steps

This library was written in Swift, so in-order for you app to compile, you need to have at least on .swift file in your source code a bridging header to avoid a runtime error like so:

![swift error](./swift-error.png)

All you have to do is:

- File > New > File
- Swift File
- Name the file whatever you wish
- When prompted to create a bridging header, do so

##### Example Podfile

- You must remove `Flipper` entirely from your Podfile
- You must have `use_frameworks!` at the top of your podfile

```ruby
platform :ios, '10.0'
require_relative '../node_modules/@react-native-community/cli-platform-ios/native_modules'
use_frameworks! # THIS IS A MUST

def add_flipper_pods!(versions = {})
  versions['Flipper'] ||= '~> 0.33.1'
  versions['DoubleConversion'] ||= '1.1.7'
  versions['Flipper-Folly'] ||= '~> 2.1'
  versions['Flipper-Glog'] ||= '0.3.6'
  versions['Flipper-PeerTalk'] ||= '~> 0.0.4'
  versions['Flipper-RSocket'] ||= '~> 1.0'

  pod 'FlipperKit', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/FlipperKitLayoutPlugin', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/SKIOSNetworkPlugin', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/FlipperKitUserDefaultsPlugin', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/FlipperKitReactPlugin', versions['Flipper'], :configuration => 'Debug'

  # List all transitive dependencies for FlipperKit pods
  # to avoid them being linked in Release builds
  pod 'Flipper', versions['Flipper'], :configuration => 'Debug'
  pod 'Flipper-DoubleConversion', versions['DoubleConversion'], :configuration => 'Debug'
  pod 'Flipper-Folly', versions['Flipper-Folly'], :configuration => 'Debug'
  pod 'Flipper-Glog', versions['Flipper-Glog'], :configuration => 'Debug'
  pod 'Flipper-PeerTalk', versions['Flipper-PeerTalk'], :configuration => 'Debug'
  pod 'Flipper-RSocket', versions['Flipper-RSocket'], :configuration => 'Debug'
  pod 'FlipperKit/Core', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/CppBridge', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/FBCxxFollyDynamicConvert', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/FBDefines', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/FKPortForwarding', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/FlipperKitHighlightOverlay', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/FlipperKitLayoutTextSearchable', versions['Flipper'], :configuration => 'Debug'
  pod 'FlipperKit/FlipperKitNetworkPlugin', versions['Flipper'], :configuration => 'Debug'
end

# Post Install processing for Flipper
def flipper_post_install(installer)
  installer.pods_project.targets.each do |target|
    if target.name == 'YogaKit'
      target.build_configurations.each do |config|
        config.build_settings['SWIFT_VERSION'] = '4.1'
      end
    end
  end
end

target 'AwesomeProject' do
  # Pods for AwesomeProject
  pod 'Giphy', :modular_headers => true
  pod 'FBLazyVector', :path => "../node_modules/react-native/Libraries/FBLazyVector"
  pod 'FBReactNativeSpec', :path => "../node_modules/react-native/Libraries/FBReactNativeSpec"
  pod 'RCTRequired', :path => "../node_modules/react-native/Libraries/RCTRequired"
  pod 'RCTTypeSafety', :path => "../node_modules/react-native/Libraries/TypeSafety"
  pod 'React', :path => '../node_modules/react-native/'
  pod 'React-Core', :path => '../node_modules/react-native/'
  pod 'React-CoreModules', :path => '../node_modules/react-native/React/CoreModules'
  pod 'React-Core/DevSupport', :path => '../node_modules/react-native/'
  pod 'React-RCTActionSheet', :path => '../node_modules/react-native/Libraries/ActionSheetIOS'
  pod 'React-RCTAnimation', :path => '../node_modules/react-native/Libraries/NativeAnimation'
  pod 'React-RCTBlob', :path => '../node_modules/react-native/Libraries/Blob'
  pod 'React-RCTImage', :path => '../node_modules/react-native/Libraries/Image'
  pod 'React-RCTLinking', :path => '../node_modules/react-native/Libraries/LinkingIOS'
  pod 'React-RCTNetwork', :path => '../node_modules/react-native/Libraries/Network'
  pod 'React-RCTSettings', :path => '../node_modules/react-native/Libraries/Settings'
  pod 'React-RCTText', :path => '../node_modules/react-native/Libraries/Text'
  pod 'React-RCTVibration', :path => '../node_modules/react-native/Libraries/Vibration'
  pod 'React-Core/RCTWebSocket', :path => '../node_modules/react-native/'

  pod 'React-cxxreact', :path => '../node_modules/react-native/ReactCommon/cxxreact'
  pod 'React-jsi', :path => '../node_modules/react-native/ReactCommon/jsi'
  pod 'React-jsiexecutor', :path => '../node_modules/react-native/ReactCommon/jsiexecutor'
  pod 'React-jsinspector', :path => '../node_modules/react-native/ReactCommon/jsinspector'
  pod 'ReactCommon/callinvoker', :path => "../node_modules/react-native/ReactCommon"
  pod 'ReactCommon/turbomodule/core', :path => "../node_modules/react-native/ReactCommon"
  pod 'Yoga', :path => '../node_modules/react-native/ReactCommon/yoga', :modular_headers => true

  pod 'DoubleConversion', :podspec => '../node_modules/react-native/third-party-podspecs/DoubleConversion.podspec'
  pod 'glog', :podspec => '../node_modules/react-native/third-party-podspecs/glog.podspec'
  pod 'Folly', :podspec => '../node_modules/react-native/third-party-podspecs/Folly.podspec'

  target 'AwesomeProjectTests' do
    inherit! :complete
    # Pods for testing
  end

  use_native_modules!

  # Enables Flipper.
  #
  # Note that if you have use_frameworks! enabled, Flipper will not work and
  # you should disable these next few lines.
  # add_flipper_pods!
  # post_install do |installer|
  #   flipper_post_install(installer)
  # end
  # --- ENSURE ALL THIS CODE IS COMMENTED
end

target 'AwesomeProject-tvOS' do
  # Pods for AwesomeProject-tvOS

  target 'AwesomeProject-tvOSTests' do
    inherit! :search_paths
    # Pods for testing
  end
end
```

### Android Installation

For versions below 0.60.0, follow the linking instructions above.

You must add the following to your application `build.gradle`

```gradle
repositories {
  maven {
    url "http://giphy.bintray.com/giphy-sdk"
  }
}
```

Your `minSdkVersion` must be >= 19.

## Usage

This library offers an imperative api wrapper around the native Giphy SDK

```typescript
import { GiphyUI } from 'react-native-giphy-ui';

// Configure the client with the api key
GiphyUI.configure('GIPHY_API_KEY');

// Present the ui
GiphyUI.present(
  {
    theme: 'dark',
    layout: 'carousel',
    rating: 'ratedPG13',
    showConfirmationScreen: false,
    mediaTypes: ['gfs'],
  },
  selectedMedia => {
    console.log('Picked media', selectedMedia);
  }
);
```

## Reference

### GiphyUI

- [`present`](#present)
- [`configure`](#configure)

#### `present(config: GiphyPresentConfig, onSelect: (media: GiphyGif) => void, onDismiss?: Callback)`

Shows the `GiphyViewController` with a customized configuration, and an on select/dismiss callback.

```typescript
GiphyUI.present(
  {
    theme: 'light',
  },
  media => {
    console.log(media);
  }
);
```

**GiphyPresentConfig**

| Name                   | Type                                                                         | Default   | Description                                                                        |
| ---------------------- | ---------------------------------------------------------------------------- | --------- | ---------------------------------------------------------------------------------- |
| theme                  | enum('light', 'dark')                                                        | light     | The theme of the gif picker                                                        |
| layout                 | enum('carousel', 'waterfall')                                                | waterfall | The layout of the gif picker                                                       |
| rating                 | enum('nsfw', 'ratedPG13', 'ratedPG', 'unrated', 'ratedR', 'ratedY, 'ratedG') | ratedPG   | set a specific content rating for the search results.                              |
| trayHeightMultiplier   | number                                                                       | 0.7       | height for the tray's "snap point" as a ratio of the GiphyViewController's height. |
| showConfirmationScreen | boolean                                                                      | false     | show a secondary confirmation screen when the user taps a GIF.                     |
| shouldLocalizeSearch   | boolean                                                                      | false     | localize the search results based on phone settings                                |
| mediaTypes             | enum('gifs', 'stickers', 'text', 'emoji')[]                                  | ['gifs']  | Set the content type(s) you'd like to show                                         |

**GiphyGif**

Please view the [types file](./src/types.ts)

**Callback**

```js
type Callback = (() => void) | null;
```

#### configure(apiKey: string)

Configures the api client with a given api key.

### GiphyCache (iOS Only)

- [`setSetting`](#setSetting)
- [`downloadAssetUrl`](#downloadAssetUrl)

#### `setSetting(setting: 'memoryOnly' | 'diskOnly')`

This controls how the GIFs are cached on the device. If you only want to store the GIF caches in memory you can:

```js
GiphyCache.setSetting('memoryOnly');
```

Similarly, if you want to store the cache on the disk; you can:

```js
GiphyCache.setSetting('diskOnly');
```

#### `downloadAssetUrl(url: string): Promise<Asset>`

Returns the raw image data for a cached image.

```js
const asset = await GiphyCache.downloadAssetUrl(url);
console.log(asset.data); // base 64 encoded data
```

**Asset**

```ts
type Asset = {
  width: number;
  height: number;
  data: string; // base 64 image
};
```

#### `setDiskCacheByteLimit(limit: number)`

The default disk cache limit is 300mb, if you want to increase it, call on this method like so:

```js
const oneGB = 1000 * 1000 * 1000;
GiphyCache.setDiskCacheByteLimit(oneGB);
```

#### GiphyMediaView (iOS Only)

> This API has not been fully implemented yet.

A wrapper around `GPHMediaView` to display a Gif Media

> **Note: you can use a regular React Native Image to display the gif**

```tsx
import {GiphyMediaView} from 'react-native-giphy-ui'

const App = () => {
  //...
  const media = //

  return (
    <GiphyMediaView media={media} />
  )
}
```

## Acknowledgments

- This library is based off of [giphy-ios-sdk-ui](https://github.com/Giphy/giphy-ios-sdk-ui-example/blob/master/Docs.md) and [giphy-android-sdk-ui](https://github.com/Giphy/giphy-android-sdk-ui-example/blob/master/Docs.md)

### Roadmap

- [ ] Extend coverage of the native api
- [ ] Improve documentation
- [ ] Unit tests
- [ ] Flow support

### Contribution

Please visit the [CONTRIBUTING.md](CONTRIBUTING.md) file.

## License

MIT
