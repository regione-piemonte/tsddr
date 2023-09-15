module.exports = {
  hooks: {
    'after:release': []
  },
  git: {
    commitMessage: 'chore: release v${version}',
    commitArgs: '--no-verify',
    requireCleanWorkingDir: false,
    requireUpstream: false,
    commit: true,
    push: true,
    tag: true
  },
  'npm': {
    publish: false
  },
  plugins: {
    '@release-it/conventional-changelog': {
      preset: 'angular',
      infile: 'CHANGELOG.md'
    },
    './scripts/release_it_gitlab.js': {
      hostname: 'production.eng.it',
      path: '/gitlab/api/v4/projects/7806/releases',
      releaseUrl: 'https://production.eng.it/gitlab/DS_WEB/csi-piemonte/scriva-pubblicazione-e-osservazioni/releases',
      releaseName: 'Release ${version}',
      tokenRef: 'GITLAB_TOKEN'
    }
  }
};
