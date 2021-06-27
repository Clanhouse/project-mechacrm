const getColor = (color, theme) => {
  switch (color) {
    case 'light':
      return theme.color.light;
    case 'blue':
      return theme.color.blue;
  }
};

export default getColor;
