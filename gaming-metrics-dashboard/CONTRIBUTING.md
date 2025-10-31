# Contributing to Gaming Metrics Dashboard

First off, thank you for considering contributing to Gaming Metrics Dashboard! It's people like you that make this tool better for everyone.

## Code of Conduct

By participating in this project, you are expected to uphold our Code of Conduct:
- Be respectful and inclusive
- Welcome newcomers and help them get started
- Accept constructive criticism gracefully
- Focus on what is best for the community

## How Can I Contribute?

### Reporting Bugs

Before creating bug reports, please check the existing issues to avoid duplicates. When you create a bug report, include as many details as possible:

- **Use a clear and descriptive title**
- **Describe the exact steps to reproduce the problem**
- **Provide specific examples**
- **Describe the behavior you observed and what you expected**
- **Include screenshots if possible**
- **Include your environment details** (OS, Node version, browser, etc.)

### Suggesting Enhancements

Enhancement suggestions are tracked as GitHub issues. When creating an enhancement suggestion:

- **Use a clear and descriptive title**
- **Provide a detailed description of the suggested enhancement**
- **Explain why this enhancement would be useful**
- **List any alternatives you've considered**

### Pull Requests

1. **Fork the repository** and create your branch from `main`
2. **Install dependencies**: `npm install`
3. **Make your changes**
4. **Add tests** for any new functionality
5. **Ensure all tests pass**: `npm test`
6. **Update documentation** if needed
7. **Commit your changes** with a clear commit message
8. **Push to your fork** and submit a pull request

#### Pull Request Guidelines

- Follow the existing code style
- Write clear, concise commit messages
- Update the README.md if needed
- Add tests for new features
- Ensure CI/CD pipeline passes
- Keep pull requests focused on a single issue

### Development Setup

```bash
# Clone your fork
git clone https://github.com/reyisjones/GamingMetrics.git
cd gaming-metrics-dashboard

# Install dependencies
npm install

# Start development server
npm run dev

# Run tests
npm test

# Run tests in watch mode
npm test -- --watch
```

### Project Structure

```
src/
├── components/     # React components
├── pages/          # Page components
├── api/            # API configuration
├── data/           # Sample data
├── test/           # Unit tests
├── App.jsx         # Root component
├── main.jsx        # Entry point
└── theme.js        # MUI theme
```

### Coding Standards

#### JavaScript/React

- Use functional components with hooks
- Follow React best practices
- Use meaningful variable and function names
- Add JSDoc comments for complex functions
- Keep components small and focused

#### Testing

- Write tests for all new features
- Aim for >80% code coverage
- Use descriptive test names
- Follow AAA pattern (Arrange, Act, Assert)

```jsx
it('displays game name correctly', () => {
  // Arrange
  const game = { name: 'Test Game', avgFps: 144 };
  
  // Act
  render(<GameCard game={game} />);
  
  // Assert
  expect(screen.getByText('Test Game')).toBeInTheDocument();
});
```

#### Commit Messages

Follow conventional commits:

- `feat:` - New feature
- `fix:` - Bug fix
- `docs:` - Documentation changes
- `test:` - Test changes
- `refactor:` - Code refactoring
- `style:` - Code style changes
- `chore:` - Build/config changes

Examples:
```
feat: add temperature warning threshold
fix: correct CPU usage calculation
docs: update installation instructions
test: add tests for MetricsChart component
```

### Adding New Metrics

To add a new metric to the dashboard:

1. Update `src/data/sampleGames.json`:
```json
{
  "id": 1,
  "name": "Game",
  "newMetric": 42
}
```

2. Update `GameCard.jsx` to display it:
```jsx
<Box sx={{ display: 'flex', justifyContent: 'space-between' }}>
  <Typography>New Metric</Typography>
  <Typography>{game.newMetric}</Typography>
</Box>
```

3. Add a chart in `Dashboard.jsx`:
```jsx
<MetricsChart title="New Metric" dataKey="newMetric" games={games} />
```

4. Write tests in `src/test/`:
```jsx
it('displays new metric', () => {
  renderWithTheme(<GameCard game={mockGame} />);
  expect(screen.getByText('New Metric')).toBeInTheDocument();
});
```

### Documentation

- Update README.md for new features
- Add JSDoc comments for public APIs
- Update CHANGELOG.md
- Include examples where helpful

### Questions?

Feel free to:
- Open an issue for questions
- Start a discussion on GitHub Discussions
- Reach out to maintainers

## Recognition

Contributors will be recognized in:
- README.md contributors section
- GitHub contributors page
- Release notes

Thank you for contributing! 🎮
