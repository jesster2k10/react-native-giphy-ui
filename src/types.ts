export type GiphyRating = 'y' | 'g' | 'pg' | 'pg-13' | 'r';

export interface GiphyGif {
  type: string;
  id: string;
  slug: string;
  url: string;
  bitly_url: string;
  embed_url: string;
  username: string;
  source: string;
  rating: GiphyRating;
  content_url: string;
  source_tld?: string;
  source_post_url: string;
  update_datetime: Date;
  create_datetime: Date;
  import_datetime: Date;
  trending_datetime: Date;
  images: GiphyImages;
  title: string;
}

export interface GiphyImagePreviewGif {
  url: string;
  width: string;
  height: string;
}

export interface GiphyImagePreview {
  mp4: string;
  mp4_size: string;
  width: string;
  height: string;
}

export interface GiphyImageLooping {
  mp4: string;
}

export interface GiphyImageOriginalStill {
  url: string;
  width: string;
  height: string;
}

export interface GiphyImageOriginal {
  url: string;
  width: string;
  height: string;
  frames: string;
  mp4: string;
  mp4_size: string;
  webp: string;
  webp_size: string;
}

export interface GiphyImageDownsized {
  url: string;
  width: string;
  height: string;
  size: string;
  mp4?: string;
  mp4_size?: string;
}

export interface GiphyImageFixedSize {
  url: string;
  width: string;
  height: string;
  size: string;
  mp4?: string;
  mp4_size?: string;
  webp?: string;
  webp_size?: string;
}

export interface GiphyImages {
  preview_gif: GiphyImagePreviewGif;
  preview: GiphyImagePreview;
  looping: GiphyImageLooping;
  original_still: GiphyImageOriginalStill;
  original: GiphyImageOriginal;
  downsized: GiphyImageDownsized;
  downsized_still: GiphyImageDownsized;
  downsized_large: GiphyImageDownsized;
  downsized_medium: GiphyImageDownsized;
  downsized_small: GiphyImageDownsized;
  fixed_height_small: GiphyImageFixedSize;
  fixed_height_small_still: GiphyImageFixedSize;
  fixed_width_small_still: GiphyImageFixedSize;
  fixed_width_downsampled: GiphyImageFixedSize;
  fixed_with_small: GiphyImageFixedSize;
  fixed_with_still: GiphyImageFixedSize;
  fixed_width: GiphyImageFixedSize;
  fixed_height_downsampled: GiphyImageFixedSize;
  fixed_height_still: GiphyImageFixedSize;
  fixed_height: GiphyImageFixedSize;
}
